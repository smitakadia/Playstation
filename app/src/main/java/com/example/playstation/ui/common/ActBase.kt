package com.social.kotlinstructure.ui.common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.playstation.R
import com.example.playstation.ui.activity.ActPlayStationList
import com.example.playstation.utils.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

open class ActBase<T : ViewDataBinding> : AppCompatActivity() {
    /* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
     }*/
    protected lateinit var mBinding: T
    //private var authViewModel: AuthenticationViewModel?
    private lateinit var progressDialog: Dialog

    protected fun bindView(layoutId: Int) {

        mBinding = DataBindingUtil.setContentView(this, layoutId)

    }

    fun isInternetAvailable(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun showErrorAlert(msg: String?, logout: String?) {

        val alertDialog = AlertDialog.Builder(this)
        var message = msg
        var isLogout = logout

        if (isLogout.isNullOrEmpty())
            isLogout = Constants.NO

        if (isLogout.equals(Constants.YES, true)) {
            message = getString(R.string.string_force_logout)
            alertDialog.setTitle(getString(R.string.string_authentication_failed))
        }

        alertDialog.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Okay") { dialog, which ->

                if (isLogout.equals(Constants.YES, true)) {

                    val i = Intent(this@ActBase, ActPlayStationList::class.java)
                    i.putExtra(Constants.INTENT_FORCED_LOGOUT, false)
                    finishAffinity()
                    startActivity(i)
                }
            }

        alertDialog.create().show()
    }

    fun showAlert(msg: String?, isFinish: Boolean) {

        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Okay") { dialog, which ->
                if (isFinish)
                    finish()
            }

        alertDialog.create().show()
    }

    fun showToast(msg: String?) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun showNoData(
        recyclerView: RecyclerView,
        noDataView: View,
        isNullOrEmpty: Boolean,
        msg: String?
    ) {

        if (isNullOrEmpty) {

            recyclerView.visibility = View.GONE
            noDataView.visibility = View.VISIBLE

        } else {

            recyclerView.visibility = View.VISIBLE
            noDataView.visibility = View.GONE
        }
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    protected fun showLoader(context: Context) {
        progressDialog = Dialog(context)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(R.layout.layout_custom_loader)
        progressDialog.window?.setDimAmount(0.75f)
        progressDialog.show()
    }

    protected fun dismissLoader() {
        if (::progressDialog.isInitialized)
            progressDialog.dismiss()
    }

    fun startMyActivity(context: Context,intent: Intent){
        startActivity(intent)
    }

    fun startMyActivityReferesh(context: Context,intent: Intent){
        startActivity(intent)
        finish()
    }

 /*   fun webCallLogout(id: String) {

        val logoutRequestModel = LogoutRequestModel()

        logoutRequestModel.userId = id

        authViewModel?.logout(logoutRequestModel)?.observe(this, Observer { resultModel ->

            if (resultModel == null) {
                //showErrorAlert(getString(R.string.web_response_null),resultModel?.forceLogout)
                return@Observer
            }

            if (resultModel.status.equals(Constants.WEB_FAIL, false)) {
                //showErrorAlert(resultModel.msg,resultModel.forceLogout)
                return@Observer
            }

            CM.clearSharedPreference(applicationContext)
            CM.clearDatabase(mDatabase)
        })
    }
*/
    fun displayShortToast(context: Context, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun displayLongToast(context: Context, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }

    open fun parseDateToddMMyyyy(time: String?): String? {
        val inputPattern = "dd/MM/yyyy"
        val outputPattern = "yyyy-MM-dd"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }

}