package com.segunfrancis.fintrack.ui.savings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDefaults.dateFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segunfrancis.fintrack.R
import com.segunfrancis.fintrack.ui.components.CustomInputField
import com.segunfrancis.fintrack.ui.theme.Orange
import com.segunfrancis.fintrack.ui.theme.White
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CreateSavingsScreen(
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    var savingFor by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var goalAmount by remember { mutableStateOf("") }
    var showCategoryDropdown by remember { mutableStateOf(false) }
    val categories = listOf("Rent", "Vacation", "Car", "Gadget", "Other")
    var selectedSavingType by remember { mutableStateOf("Daily") }
    val savingTypes = listOf("Daily", "Weekly", "Monthly", "Manually")
    var shouldShowDate by remember { mutableStateOf(false) }
    val dateState = rememberDateRangePickerState(
        initialDisplayedMonthMillis = System.currentTimeMillis(),
        yearRange = 2025..2025
    )
    var selectedDateRange by remember { mutableStateOf("") }
    val bottomSheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(bottom = 85.dp)
        ) {
            Spacer(Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Orange)
                        .clickable(onClick = onBack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(180F)
                    )
                }
                Spacer(Modifier.width(14.dp))
                Text(
                    text = "2 of 4",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.55f),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier.align(Alignment.CenterVertically).fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(22.dp))
            Text(
                text = "Create your saving goal",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Setup a personal savings goal. E.g Rent, or a car or a trip.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(28.dp))
            CustomInputField(
                value = savingFor,
                onValueChange = { savingFor = it },
                label = "What are you saving for?",
                placeholder = "e.g Rent, Car, Laptop"
            )

            Spacer(Modifier.height(18.dp))
            FormLabel("What category does this belong to?")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                OutlinedTextField(
                    value = if (selectedCategory.isEmpty()) "Select a category" else selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Dropdown"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showCategoryDropdown = true },
                    shape = RoundedCornerShape(10.dp),
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = if (selectedCategory.isEmpty()) 0.4f else 1f),
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.outline,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                    )
                )

                DropdownMenu(
                    expanded = showCategoryDropdown,
                    onDismissRequest = { showCategoryDropdown = false },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = {
                                selectedCategory = cat
                                showCategoryDropdown = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(18.dp))
            CustomInputField(
                value = goalAmount,
                onValueChange = { goalAmount = it },
                label = "What is your goal amount?",
                placeholder = "200,000",
                keyboardType = KeyboardType.Decimal,
                prefixText = "₦"
            )

            Spacer(Modifier.height(18.dp))
            FormLabel("How will you prefer to save?")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                savingTypes.forEach { type ->
                    val isSelected = selectedSavingType == type
                    InputChip(
                        selected = isSelected,
                        onClick = { selectedSavingType = type },
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 1.dp,
                            color = if (isSelected) Orange else MaterialTheme.colorScheme.background
                        ),
                        colors = InputChipDefaults.inputChipColors(
                            containerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.1F),
                            selectedContainerColor = Orange.copy(alpha = 0.3F)
                        ),
                        label = {
                            Text(
                                text = type,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 15.sp
                                ),
                                modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp)
                            )
                        })
                }
            }

            Spacer(Modifier.height(18.dp))
            FormLabel("What is the duration of your goal?")
            OutlinedTextField(
                value = selectedDateRange,
                onValueChange = {},
                enabled = false,
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = "Pick date"
                    )
                },
                placeholder = {
                    Text(
                        text = "Pick a start and end date",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                        )
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { shouldShowDate = true },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onBackground,
                    disabledBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            Spacer(Modifier.height(28.dp))
        }

        if (shouldShowDate) {
            ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = { shouldShowDate = false },
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                containerColor = MaterialTheme.colorScheme.surface,
                dragHandle = {}
            ) {
                Column {
                    Button(
                        onClick = {
                            val startDate = dateFormatter().formatDate(
                                dateState.selectedStartDateMillis,
                                Locale.getDefault()
                            )
                            val endDate =
                                dateFormatter().formatDate(
                                    dateState.selectedEndDateMillis,
                                    Locale.getDefault()
                                )
                            selectedDateRange = "$startDate - $endDate"
                            shouldShowDate = false
                        },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Orange),
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = dateState.selectedEndDateMillis != null
                    ) {
                        Text(
                            text = "Save Date",
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp)
                        )
                    }
                    DateRangePicker(
                        state = dateState,
                        title = {
                            Text(
                                text = "Pick a start and end date",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier
                                    .padding(24.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        colors = DatePickerDefaults.colors(
                            selectedDayContainerColor = Orange,
                            selectedDayContentColor = White,
                            todayDateBorderColor = Orange,
                            dayInSelectionRangeContainerColor = Orange.copy(alpha = 0.4F)
                        ),
                        headline = {
                            DateRangePickerDefaults.DateRangePickerHeadline(
                                selectedStartDateMillis = dateState.selectedStartDateMillis,
                                selectedEndDateMillis = dateState.selectedEndDateMillis,
                                displayMode = dateState.displayMode,
                                dateFormatter(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Button(
                onClick = onNext,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    style = MaterialTheme.typography.labelLarge.copy(fontSize = 17.sp)
                )
            }
        }
    }
}

@Composable
fun FormLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier.padding(bottom = 4.dp)
    )
}
