package com.sleep.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.content.res.AssetFileDescriptor
import android.databinding.DataBindingUtil
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.util.SparseArray
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.Toast
import com.sleep.R
import com.sleep.adapter.ViewPagerAdapter
import com.sleep.constants.CommonConstants
import com.sleep.databinding.ActivityMainBinding
import com.sleep.ui.fragment.MainFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainFragment.OnFragmentInteractionListener {

    val prefs: SharedPreferences by lazy {
        getSharedPreferences(CommonConstants.DEFAULT_PRE, Context.MODE_PRIVATE)
    }

    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    val map: SparseArray<String> = SparseArray()
    val bgList: List<Int> = listOf(R.mipmap.background_small_rain, R.mipmap.background_big_rain, R.mipmap.background_thunder)

    val list: MutableList<Fragment> = mutableListOf()

    val mediaPlayer: MediaPlayer = MediaPlayer()

    val defaultTriggerAtMillis: Int = 30

    val alarmManager: AlarmManager by lazy {
        getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    val action: String = "com.sleep.end"

    val pendingIntent: PendingIntent by lazy {
        val intent: Intent = Intent()
        intent.action = action
        PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    val alpha: Int = 0X33FFFFFF

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (mediaPlayer.isPlaying)
                mediaPlayer.stop()
            mediaPlayer.reset()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filter: IntentFilter = IntentFilter()
        filter.addAction(action)
        registerReceiver(receiver, filter)

        map.put(R.mipmap.background_thunder, "sound/yisell_sound_thunder.mp3")
        map.put(R.mipmap.background_big_rain, "sound/yisell_sound_big_rain.mp3")
        map.put(R.mipmap.background_small_rain, "sound/yisell_sound_small_rain.mp3")

        bgList.mapTo(list) { MainFragment.newInstance(it) }

        binding.toolbar.setTitle(R.string.app_name)
        val rgb = resources.getColor(R.color.colorPrimary)
        updateToolColors(rgb, rgb)


        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
        binding.viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                updateSound(position)
            }
        })
        binding.viewPager.offscreenPageLimit = list.size - 1
        binding.viewPager.adapter = ViewPagerAdapter(list, supportFragmentManager)
        binding.viewPager.currentItem = 1
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar == null)
                    return
                val progress: Int = (seekBar.progress + 1) * 10
                Toast.makeText(this@MainActivity, "$progress 分钟后停止", Toast.LENGTH_SHORT).show()
                val trigger = prefs.getInt(CommonConstants.TRIGGER_KEY, defaultTriggerAtMillis)
                if (progress != trigger) {
                    prefs.edit().putInt(CommonConstants.TRIGGER_KEY, progress).apply()
                    alarmManager.cancel(pendingIntent)
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + progress * 60 * 1000, pendingIntent)
                }

                if (!mediaPlayer.isPlaying) {
                    updateSound(binding.viewPager.currentItem)
                }
            }
        })

        mediaPlayer.setOnErrorListener { mp, what, extra ->
            mp?.reset()
            true
        }

        updateSound(0)
    }

    private fun updateToolColors(rgb: Int, textColor: Int?) {
        binding.navView.getHeaderView(0).setBackgroundColor(rgb)
        binding.toolbar.setBackgroundColor(rgb and alpha)
        if (textColor == null) {
            binding.toolbar.setTitleTextColor(rgb)
        } else {
            binding.toolbar.setTitleTextColor(textColor)
        }
    }

    private fun updateSound(position: Int) {
        val fragment = list[position]
        if (fragment is MainFragment) {
            val lightMutedSwatch = fragment.lightMutedSwatch

            if (lightMutedSwatch?.rgb != null) {
                val rgb = lightMutedSwatch?.rgb
                if (rgb != null) {
                    updateToolColors(rgb, lightMutedSwatch?.bodyTextColor)
                }
            }

            play(map[fragment.resId])
        }
    }


    fun play(path: String) {
        mediaPlayer.reset()
        mediaPlayer.isLooping = true
        val fd: AssetFileDescriptor = assets.openFd(path)
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
        mediaPlayer.start()


        val trigger = prefs.getInt(CommonConstants.TRIGGER_KEY, defaultTriggerAtMillis)
        alarmManager.cancel(pendingIntent)
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + trigger * 60 * 1000, pendingIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)

        mediaPlayer.reset()
        mediaPlayer.release()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_mark) {
            Toast.makeText(this, "去评分", Toast.LENGTH_SHORT).show()
            mark()
        } else if (id == R.id.nav_about) {
            AboutActivity.startActivity(this)
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun mark() {
        try {
            val uri = Uri.parse("market://details?id=" + packageName)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "没有找到应用商店", Toast.LENGTH_SHORT).show()
        }
    }


    override fun notifyPalette(fragment: Fragment, swatch: Palette.Swatch?) {
        if (fragment is MainFragment) {
            val indexOf = list.indexOf(fragment)
            val currentItem = binding.viewPager.currentItem
            if (indexOf == currentItem) {
                val lightMutedSwatch = fragment.lightMutedSwatch

                if (lightMutedSwatch?.rgb != null) {
                    val rgb: Int? = lightMutedSwatch?.rgb
                    if (rgb != null) {
                        updateToolColors(rgb, lightMutedSwatch?.bodyTextColor)
                    }
                }
            }
        }
    }

}
