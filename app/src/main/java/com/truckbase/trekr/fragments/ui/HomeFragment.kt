package com.truckbase.trekr.fragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.truckbase.trekr.R
import com.truckbase.trekr.data.adapterdata.NewArrivalsData
import com.truckbase.trekr.data.repository.AuthRepository
import com.truckbase.trekr.databinding.FragmentHomeBinding
import com.truckbase.trekr.domain.model.CategoryResponse
import com.truckbase.trekr.domain.model.ProductsResponse
import com.truckbase.trekr.utils.Constants.ARG_NAME_KEY
import com.truckbase.trekr.utils.adapters.CategoryAdapter
import com.truckbase.trekr.utils.adapters.NewArrivalsAdapter
import com.truckbase.trekr.utils.adapters.ProductsAdapter
import com.truckbase.trekr.utils.snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        //New Arrivals
        var layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        var itemList = listOf(
            NewArrivalsData(R.drawable.mi_9t, "Xiaomi Mi 9T", "Xiaomi Phones", "$250"),
            NewArrivalsData(R.drawable.mi_9t, "Xiaomi Mi 9T", "Xiaomi Phones", "$250"),
            NewArrivalsData(R.drawable.mi_9t, "Xiaomi Mi 9T", "Xiaomi Phones", "$250"),
            NewArrivalsData(R.drawable.mi_9t, "Xiaomi Mi 9T", "Xiaomi Phones", "$250"),
            NewArrivalsData(R.drawable.mi_9t, "Xiaomi Mi 9T", "Xiaomi Phones", "$250"),
            NewArrivalsData(R.drawable.mi_9t, "Xiaomi Mi 9T", "Xiaomi Phones", "$250")
        )


        binding.apply {
            newArrivalsVP.adapter = NewArrivalsAdapter(itemList)
            newArrivalsVP.layoutManager = layoutManager
            newArrivalsVP.setHasFixedSize(true)
             /*/
             Get List Of Categories
             Pass The The Property `name` into a RecyclerView
             ViewHolder - MaterialButton
              */
            authRepository.getCategory().enqueue(object : Callback<CategoryResponse> {
                override fun onResponse(
                    call: Call<CategoryResponse>,
                    response: Response<CategoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val categoryList = response.body()
                        categoryList.let {
                            if (it != null) {
                                getCategoryNames(it)
                            } else {
                                println("Null Response")
                                println(response.body())
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                    println("${t.message}")
                    println("${t.cause}")
                }


            })
        }


        return binding.root
    }



    private fun getCategoryNames(categoryResponseList: List<CategoryResponse.CategoryResponseItem>){
        val adapter = CategoryAdapter(categoryResponseList){categoryID->
             getProducts(categoryID.toString())
        }
        binding.categoryRv.adapter = adapter
        binding.categoryRv.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        binding.categoryRv.setHasFixedSize(true)
    }

    private fun showProducts(productList: List<ProductsResponse>?){
        binding.productsRv.apply {
            layoutManager = object :GridLayoutManager(activity?.applicationContext,2,LinearLayoutManager.VERTICAL,false){
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            adapter = ProductsAdapter(productList){}
            setHasFixedSize(true)
        }
    }

    private fun getProducts(categoryID : String){
        authRepository.getProductsByCategory(categoryID).enqueue(object : Callback<List<ProductsResponse>>{
            override fun onResponse(
                call: Call<List<ProductsResponse>>,
                response: Response<List<ProductsResponse>>
            ) {
                if (response.isSuccessful){
                    val gson = Gson()
                    val responseJson = gson.toJson(response.body())


                    try {
                        val productList = gson.fromJson<List<ProductsResponse.ProductsResponseItem>>(
                            responseJson,
                            object : TypeToken<List<ProductsResponse.ProductsResponseItem>>(){}.type
                        )
                        showProducts(response.body())
                        for (product in productList){
                            println("Product ID: ${product.id}")
                        }
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
                
                else{
                    view?.snackbar("${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<ProductsResponse>>, t: Throwable) {
                view?.snackbar("${t.message}")
                println("${t.message}")
                println("${t.cause}")
            }

        })
    }

    companion object {
        fun newInstance(dataName: String, dataAvatar: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(
                ARG_NAME_KEY, dataName
            )
            args.putString(
                ARG_NAME_KEY, dataAvatar
            )
            fragment.arguments = args

            return fragment
        }
    }

}