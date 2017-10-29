package com.example.dani.buyapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
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

        val adapter = ArrayAdapter<Int>(this,android.R.layout.simple_spinner_dropdown_item)
        var i: Int = 1
        for (i in i..stock.toString().toInt()){
            adapter.add(i)
        }

        sp_num_selection.adapter = adapter
        sp_num_selection.setSelection(data.toString().toInt()-1)

        tv_stock.text = stock.toString()
    }


     fun finishForResult(result: Int) {
         if (result== Activity.RESULT_OK){
             var intentResult: Intent = Intent()
             var data = sp_num_selection.selectedItem.toString().toInt()
             intentResult.putExtra("result_ok",data)
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
