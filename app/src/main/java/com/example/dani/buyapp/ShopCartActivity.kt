package com.example.dani.buyapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class ShopCartActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0?.id){
            cancel_btn.id -> finishForResult(Activity.RESULT_CANCELED)
            btn_buy.id -> finishForResult(Activity.RESULT_OK)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_buy.setOnClickListener(this)
        cancel_btn.setOnClickListener(this)
        cancel_btn.visibility = View.VISIBLE

        val bundle: Bundle = intent.extras
        val data = bundle.get("quantity")
        val stock = bundle.get("stock")
        et_qty_buy.setText(data.toString())
        tv_stock.text = stock.toString()

    }


     fun finishForResult(result: Int) {
         if (result== Activity.RESULT_OK){
             var intentResult: Intent = Intent()
             intentResult.putExtra("result_ok",et_qty_buy.text.toString().toInt())
             setResult(result,intentResult)
             super.finish()
         }else{
             var intentResult: Intent = Intent()
             intentResult.putExtra("result_ko","Operacion cancelada")
             setResult(result,intentResult)
             super.finish()
         }

    }


}
