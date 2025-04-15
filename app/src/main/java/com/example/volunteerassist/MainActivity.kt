
// MainActivity.kt
package com.example.volunteerassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                VolunteerReportScreen()
            }
        }
    }
}

// Define colors
val PrimaryBlue = Color(0xFF1976D2)
val LightBlue = Color(0xFF64B5F6)
val PrimaryGreen = Color(0xFF4CAF50)
val LightGreen = Color(0xFF81C784)
val Gray = Color(0xFF9E9E9E)
val LightGray = Color(0xFFEEEEEE)
val TextDark = Color(0xFF424242)

// Sample data model
data class VolunteerCompany(
    val name: String,
    val hours: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolunteerReportScreen() {
    // Sample data - would be replaced with real data
    val totalHours = 45
    val targetHours = 75
    val volunteerCompanies = listOf(
        VolunteerCompany("Local Food Bank", 15),
        VolunteerCompany("Animal Shelter", 12),
        VolunteerCompany("Community Garden", 8),
        VolunteerCompany("Youth Mentoring", 6),
        VolunteerCompany("Beach Cleanup", 4)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Volunteer Impact Dashboard") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Greetings
            item {
                Text(
                    text = "Hello, Joe",
                    fontSize = 50.sp,
                )
            }
            // Summary Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Hours Card
                    SummaryCard(
                        title = "Total Hours",
                        value = totalHours.toString(),
                        gradient = Brush.linearGradient(listOf(PrimaryBlue, LightBlue)),
                        modifier = Modifier.weight(1f)
                    )

                    // Companies Card
                    SummaryCard(
                        title = "Companies",
                        value = volunteerCompanies.size.toString(),
                        gradient = Brush.linearGradient(listOf(PrimaryGreen, LightGreen)),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Progress Chart Card
            item {
                ProgressChartCard(
                    totalHours = totalHours,
                    targetHours = targetHours
                )
            }

            // Companies List Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Volunteer Organizations",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Companies List
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            volunteerCompanies.forEach { company ->
                                CompanyItem(company = company)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SummaryCard(
    title: String,
    value: String,
    gradient: Brush,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = value,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ProgressChartCard(
    totalHours: Int,
    targetHours: Int
) {
    val progressPercent = (totalHours.toFloat() / targetHours * 100).toInt()
    val completedAngle = 360f * (totalHours.toFloat() / targetHours)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Progress Towards Goal",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextDark,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Target: ",
                    fontSize = 16.sp,
                    color = TextDark
                )
                Text(
                    text = "$targetHours hours",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue
                )
                Text(
                    text = " this quarter",
                    fontSize = 16.sp,
                    color = TextDark,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "$progressPercent%",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                // Custom Pie Chart
                ComposeDonutChart(
                    completedPercentage = totalHours.toFloat() / targetHours
                )

                // Center Text
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$totalHours/$targetHours",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                    Text(
                        text = "hours",
                        fontSize = 16.sp,
                        color = TextDark
                    )
                }
            }

            // Legend
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Completed Legend
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(PrimaryBlue, RoundedCornerShape(2.dp))
                    )
                    Text(
                        text = "Completed",
                        fontSize = 14.sp,
                        color = TextDark,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                // Remaining Legend
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(LightGray, RoundedCornerShape(2.dp))
                    )
                    Text(
                        text = "Remaining",
                        fontSize = 14.sp,
                        color = TextDark,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeDonutChart(
    completedPercentage: Float
) {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
    ) {
        val width = size.width
        val height = size.height
        val thickness = width / 8

        // Background circle (remaining)
        drawArc(
            color = LightGray,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = Offset(thickness / 2, thickness / 2),
            size = Size(width - thickness, height - thickness),
            style = Stroke(width = thickness, cap = StrokeCap.Butt)
        )

        // Progress arc (completed)
        drawArc(
            color = PrimaryBlue,
            startAngle = -90f,  // Start from top (12 o'clock position)
            sweepAngle = 360f * completedPercentage,
            useCenter = false,
            topLeft = Offset(thickness / 2, thickness / 2),
            size = Size(width - thickness, height - thickness),
            style = Stroke(width = thickness, cap = StrokeCap.Round)
        )
    }
}

@Composable
fun CircularProgressIndicator(
    percentage: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
        ) {
            val radius = size.minDimension / 2
            val strokeWidth = radius * 0.2f

            // Draw background circle
            drawCircle(
                color = LightGray,
                radius = radius,
                style = Stroke(width = strokeWidth)
            )

            // Draw progress arc
            val sweepAngle = 360f * percentage
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
fun CompanyItem(company: VolunteerCompany) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = company.name,
            fontSize = 16.sp,
            color = TextDark,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "${company.hours} hrs",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBlue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VolunteerReportScreenPreview() {
    MaterialTheme {
        VolunteerReportScreen()
    }
}