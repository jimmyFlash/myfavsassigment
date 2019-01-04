package com.jimmy.myfavsassigment.ui.views.home

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj
import com.jimmy.myfavsassigment.databinding.RatingsDialogLayoutBinding


class RatingDialogFrag : DialogFragment() {

    companion object {
        fun newInstance() = RatingDialogFrag()
    }


    private var oldRating: Int = 0

    private var getRefNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null && !arguments!!.isEmpty) {
            oldRating = arguments!!.getInt("oldRating", 0)
            getRefNum = arguments!!.getInt("pos", 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        return super.onCreateView(inflater, container, savedInstanceState)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private lateinit var binding: RatingsDialogLayoutBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         super.onCreateDialog(savedInstanceState)

        val builder = AlertDialog.Builder(activity, android.R.style.Theme_Material_Light_Dialog_Alert)

         binding = RatingsDialogLayoutBinding.inflate(LayoutInflater.from(context))

        binding.editTextRating.setText(oldRating.toString())

        builder.setView(binding.root)
            /* Add action buttons */
            .setPositiveButton("Done"
            ) { dialog, id ->
//                (activity as ClickHandler)?.onItemClick(getRefNum,
//                binding.editTextRating.text.toString().toInt())
            }


        return builder.create()
    }


    override fun onStart() {
        super.onStart()
        val d = dialog as AlertDialog
        if (d != null) {
            val positiveButton = d.getButton(Dialog.BUTTON_POSITIVE) as Button
            positiveButton.setOnClickListener({
                if(binding.editTextRating.text.toString().length > 0){
                    d.dismiss()
                    (activity as ClickHandler)?.onItemClick(getRefNum, binding.editTextRating.text.toString().toInt())
                }
            })
        }
    }

    // interface for click handeling
    interface ClickHandler {
        fun onItemClick(position: Int, rating : Int)
    }

}