package com.example.dani.buyapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val SHOP_CART_ACT_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_buy.setOnClickListener(this)
        cancel_btn.visibility = View.GONE
    }

    override fun onClick(p0: View?) {
        var data = et_qty_buy.text.toString().toInt()
        var stock = tv_stock.text.toString().toInt()
        when(p0?.id){
            btn_buy.id -> {
                if (data in 1..stock){
                    val intent: Intent = Intent(this, ShopCartActivity::class.java)
                    intent.putExtra("quantity",data)
                    intent.putExtra("stock",stock)
                    startActivityForResult(intent,SHOP_CART_ACT_ID)
                }else{
                    Toast.makeText(this,"Cantidad Incorrecta",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK && requestCode==SHOP_CART_ACT_ID){
            if (data!!.hasExtra("result_ok")){
                var qtyBought: Int = data.extras.get("result_ok").toString().toInt()
                modifyStock(qtyBought)
                Toast.makeText(this,"Cantidades compradas: $qtyBought",Toast.LENGTH_LONG).show()
            }
        }else if (resultCode== Activity.RESULT_CANCELED && requestCode==SHOP_CART_ACT_ID){
            var message = data!!.extras.getString("result_ko")
            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        }
    }

    fun modifyStock(qty: Int){
        var actualStock = tv_stock.text.toString().toInt()
        var newStock = actualStock-qty
        tv_stock.text = newStock.toString()
    }
}
