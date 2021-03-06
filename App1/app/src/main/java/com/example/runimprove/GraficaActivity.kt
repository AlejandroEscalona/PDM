package com.example.runimprove

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.runimprove.databinding.ActivityGraficaBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter

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
        pieChart.setEntryLabelColor(Color.BLUE);
    }

    /**
     * @author Alejandro Escalona García
     * Setea al inicio el gráfico
     */
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
        pieChart.legend.orientation = Legend.LegendOrientation.HORIZONTAL
        pieChart.legend.isWordWrapEnabled = true
        pieChart.legend.textSize = 36f
        pieChart.legend.formSize = 30f

    }

    /**
     * @author Alejandro Escalona García
     * Rellena de datos el gráfico
     */
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
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 6f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(20f)
        pieChart.setExtraOffsets(5f, 5f, 5f, 10f)
        pieChart.animateY(1400)

        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setCenterTextSize(22f)

        pieChart.setDrawCenterText(true);
        pieChart.centerText = getString(R.string.porcentaje_grafica)

        pieChart.invalidate()

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }


}


