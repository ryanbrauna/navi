package br.com.navi.mobile


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.navi.mobile.controllers.VendedorController
import br.com.navi.mobile.models.Vendedor
import br.com.navi.mobile.utils.ApiAccessUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        val retrofitClient = ApiAccessUtils
            .getInstance("https://navi--api.herokuapp.com/")

        val endpoint = retrofitClient.create(VendedorController::class.java)
        val request = endpoint.getVendedores()

        request.enqueue(object : Callback<List<Vendedor>> {

            override fun onFailure(call: Call<List<Vendedor>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Vendedor>>,
                response: Response<List<Vendedor>>
            ) {
                tv_response.text = response.body().toString()
            }

        })
    }
}