package com.example.myalarmmanager

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var mListener: DialogDateListener? = null

    // hanya sekali dipanggil dalam fragment dan berfungsi untuk mengaitkan dengan activity pemanggil
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as DialogDateListener?
    }

    //hanya dipanggil sebelum fragment tidak lagi dikatikan dengan activity pemanggil
    override fun onDetach() {
        super.onDetach()
        if (mListener != null) {
            mListener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)
        return DatePickerDialog(activity as Context, this, year, month, date)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        mListener?.onDialogDateSet(tag, year, month, dayOfMonth)
    }

    interface DialogDateListener {
        fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int)
    }
}