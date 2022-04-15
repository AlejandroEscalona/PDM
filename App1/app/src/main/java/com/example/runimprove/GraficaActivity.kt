package com.example.runimprove

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.runimprove.databinding.ActivityGraficaBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS


class GraficaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGraficaBinding
    private lateinit var database: DatabaseHelper
    private lateinit var pieChart: PieChart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraficaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DatabaseHelper(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        pieChart = findViewById(R.id.PieChart)
        pieChart.setEntryLabelTextSize(22f)

        initPieChart()
        setDataToPieChart()
    }

    private fun initPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.text = ""
        //hollow pie chart
        pieChart.isDrawHoleEnabled = false
        pieChart.setTouchEnabled(false)
        pieChart.setDrawEntryLabels(false)
        //adding padding
        pieChart.setExtraOffsets(20f, 0f, 20f, 0f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        pieChart.legend.isWordWrapEnabled = true
        pieChart.legend.textSize = 36f
        pieChart.legend.formSize = 30f

    }

    private fun setDataToPieChart() {
        val datos = database.stadistic()
        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(datos[0].toFloat(), getString(R.string.sprints)))
        dataEntries.add(PieEntry(datos[1].toFloat(), getString(R.string.hit)))
        dataEntries.add(PieEntry(datos[2].toFloat(), getString(R.string.bajadas)))
        dataEntries.add(PieEntry(datos[3].toFloat(), getString(R.string.resistencia)))
        dataEntries.add(PieEntry(datos[4].toFloat(), getString(R.string.tecnica)))
        dataEntries.add(PieEntry(datos[5].toFloat(), getString(R.string.estiramientos)))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))
        colors.add(Color.parseColor("#FF8A65"))
        colors.add(Color.parseColor("#8bf6ff"))
        colors.add(Color.parseColor("#ffc4ff"))
        colors.add(Color.parseColor("#98ee99"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)
        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 6f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(20f)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
       // pieChart.animateY(1400, Easing.EaseInOutQuad)

        //create hole in center
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setCenterTextSize(22f)

        //add text in center
        pieChart.setDrawCenterText(true);
        pieChart.centerText = "Porcentaje por tipos de Entreno"



        pieChart.invalidate()

    }

    private fun crearGrafico() {
//        //val datos = database.stadistic()
//
//        pieChart.setUsePercentValues(true)
//        pieChart.description.text = "Gráfico de Entrenos"
//        //hollow pie chart
//        pieChart.isDrawHoleEnabled = false
//        pieChart.setTouchEnabled(false)
//        pieChart.setDrawEntryLabels(false)
//        //adding padding
//        pieChart.setExtraOffsets(20f, 0f, 20f, 20f)
//        pieChart.setUsePercentValues(true)
//        pieChart.isRotationEnabled = false
//        pieChart.setDrawEntryLabels(false)
//        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
//        pieChart.legend.isWordWrapEnabled = true
//
//        val dataEntries = ArrayList<PieEntry>()
//        dataEntries.add(PieEntry(2f, "Sprints"))
//        dataEntries.add(PieEntry(3f, "Hit"))
//        dataEntries.add(PieEntry(4f, "Bajadas"))
//        dataEntries.add(PieEntry(5f, "Resistencia"))
//        dataEntries.add(PieEntry(3f, "Técnica"))
//        dataEntries.add(PieEntry(4f, "Estiramientos"))
//
//        val dataSet = PieDataSet(dataEntries, "texto")
//        val data = PieData(dataSet)
//        pieChart.data = data


        // In Percentage
//        data.setValueFormatter(PercentFormatter())
//        dataSet.sliceSpace = 6f
      //  dataSet.colors = COLORFUL_COLORS


        //data.setValueTextSize(15f)
        //pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        //pieChart.animateY(1400, Easing.EaseInOutQuad)

//        //create hole in center
//        pieChart.holeRadius = 58f
//        pieChart.transparentCircleRadius = 61f
//        pieChart.isDrawHoleEnabled = true
//        pieChart.setHoleColor(Color.WHITE)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }


}


