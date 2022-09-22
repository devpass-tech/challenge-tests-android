package com.devpass.spaceapp.presentation.launchList

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.devpass.spaceapp.databinding.ActivityLaunchListBinding
import com.devpass.spaceapp.R
import com.devpass.spaceapp.data.api.SpaceXAPIService
import com.devpass.spaceapp.data.model.Launch
import com.devpass.spaceapp.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaunchListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLaunchListBinding

    private lateinit var adapter: LaunchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycleView()
        initLaunchList()

        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.spacexdata.com/")
        val endpoint = retrofitClient.create(SpaceXAPIService::class.java)
        val callback = endpoint.getAllLaunches()

        callback.enqueue(object : Callback<List<Launch>> {
            override fun onFailure(call: Call<List<Launch>>, t: Throwable) {
                t.message?.let { Log.d("BODY", it) }
            }

            override fun onResponse(call: Call<List<Launch>>, response: Response<List<Launch>>) {

                response.body()?.toString()?.let { Log.d("BODY", it) }
            }
        })
    }

    private fun initLaunchList() {

        val launch1 = LaunchModel("Launch 1","1", "July 03, 2020", "Success", R.drawable.crs)
        val launch2 = LaunchModel("Launch 2","2", "July 03, 2020", "Success", R.drawable.falcon_sat)
        val launch3 = LaunchModel("Launch 3","3", "July 03, 2020", "Success", R.drawable.starlink)
        val launch4 = LaunchModel("Launch 4","4", "July 03, 2020", "Success", R.drawable.spacex_dragon_crs20_patch01)
        val launch5 = LaunchModel("Launch 5","5", "July 03, 2020", "Success", R.drawable.starlink)

        var launchList: List<LaunchModel> = listOf(launch1, launch2, launch3, launch4, launch5)
        adapter.submitList(launchList)
    }

    private fun setupRecycleView() {
        adapter = LaunchListAdapter()
        binding.rvLaunches.adapter = adapter
        binding.rvLaunches.layoutManager = LinearLayoutManager(this)
    }
}