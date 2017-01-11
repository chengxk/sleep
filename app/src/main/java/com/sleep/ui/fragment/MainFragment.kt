package com.sleep.ui.fragment


import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.graphics.Palette
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.sleep.R
import com.sleep.databinding.FragmentMainBinding


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    var resId: Int = R.mipmap.background_thunder

    var lightMutedSwatch: Palette.Swatch? = null

    private var mListener: OnFragmentInteractionListener? = null


    companion object {

        fun newInstance(resId: Int): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putInt("resId", resId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            resId = arguments.getInt("resId", R.mipmap.background_thunder)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater!!, R.layout.fragment_main, container, false)

        Glide.with(this).load(resId).asBitmap().into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                binding.sleepImg.setImageBitmap(resource)

                Palette.Builder(resource).generate {
                    lightMutedSwatch = it.lightMutedSwatch

                    if (mListener != null) {
                        mListener!!.notifyPalette(this@MainFragment, it.lightMutedSwatch)
                    }
                }
            }
        })

        return binding.root
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        fun notifyPalette(fragment: Fragment, swatch: Palette.Swatch?)
    }

}
