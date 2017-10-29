package com.example.dani.buyapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_shop_cart.*

class ShopCartActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0?.id){
            btn_cancel_buy.id -> finishForResult(Activity.RESULT_CANCELED)
            btn_buy.id -> finishForResult(Activity.RESULT_OK)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_cart)
        btn_cancel_buy.setOnClickListener(this)
        btn_buy.setOnClickListener(this)

        val bundle: Bundle = intent.extras
        val data = bundle.get("quantity")
        tv_show_qty.text = data.toString()

    }


     fun finishForResult(result: Int) {
         if (result== Activity.RESULT_OK){
             var intentResult: Intent = Intent()
             intentResult.putExtra("result_ok",tv_show_qty.text.toString().toInt())
             setResult(Activity.RESULT_OK,intentResult)
             super.finish()
         }else{
             var intentResult: Intent = Intent()
             intentResult.putExtra("result_ko","Operacion cancelada")
             setResult(Activity.RESULT_CANCELED,intentResult)
             super.finish()
         }

    }


}
