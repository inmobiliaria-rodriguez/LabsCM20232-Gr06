package co.edu.udea.compumovil.gr06_20232.lab1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr06_20232.lab1.ui.theme.LabsCM20232Gr06Theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization

import androidx.compose.ui.unit.toSize
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

class PersonalDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20232Gr06Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PersonalDataForm()
                }
            }
        }
    }
}

var name by mutableStateOf("")
var surname by mutableStateOf("")
var sexInt by mutableStateOf(0)
var birthDate by mutableStateOf("")
var schoolGrade by mutableStateOf("")
var sexVal by mutableStateOf("")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataForm() {

    val calendarState = rememberSheetState()

    val context = LocalContext.current

    CalendarDialog(state = calendarState, config = CalendarConfig(
        monthSelection = true, yearSelection = true
    ), selection = CalendarSelection.Date { date ->
        Log.d("SelectedDate", "$date")
        birthDate = date.toString()
    })

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.personal_data_form_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(Icons.Rounded.Person, contentDescription = "Person Icon")
            Spacer(modifier = Modifier.width(20.dp))
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = name,
                onValueChange = { name = it },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.personal_data_names),
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start)
        {
            Icon(Icons.Rounded.AccountCircle, contentDescription = "Person Icon")
            Spacer(modifier = Modifier.width(20.dp))
            TextField(value = surname,
                onValueChange = { surname = it },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.personal_data_surname),
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(Icons.Rounded.Favorite, contentDescription = "Sex Icon")
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = stringResource(id = R.string.personal_data_sex_tittle))
            Spacer(modifier = Modifier.width(15.dp))
            RadioButton(selected = sexInt == 0, onClick = {
                sexInt = 0
                sexVal = "Masculino"
            })
            Text(text = stringResource(id = R.string.personal_data_sex_m))

            RadioButton(selected = sexInt == 1, onClick = {
                sexInt = 1
                sexVal = "Femenino"
            })
            Text(text = stringResource(id = R.string.personal_data_sex_f))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(Icons.Rounded.DateRange, contentDescription = "date Icon", modifier = Modifier.padding(10.dp))
            Text(text = stringResource(id = R.string.personal_data_birth_date_title))
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = {
                calendarState.show()
            }) {
                Text(text = "Cambiar")
            }
        }

        schoolGrade = dropDownMenu()

        OutlinedButton(onClick = { validatePersonalData(context) }) {
            Text(text = stringResource(id = R.string.ui_next_button))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenu(): String {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf(
        stringResource(id = R.string.personal_data_schoolgrade_primary),
        stringResource(id = R.string.personal_data_schoolgrade_secondary),
        stringResource(id = R.string.personal_data_schoolgrade_university),
        stringResource(id = R.string.personal_data_schoolgrade_other)
    )
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Edit, contentDescription = "schoolgrade Icon")
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedTextField(value = selectedText,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .height(60.dp)
                    .onGloballyPositioned { coordinates ->
                        textfieldSize = coordinates.size.toSize()
                    },
                label = { Text(stringResource(id = R.string.personal_data_schoolgrade_title)) },
                trailingIcon = {
                    Icon(icon, "contentDescription", Modifier.clickable { expanded = !expanded })
                },
                readOnly = true
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(text = { Text(text = label) }, onClick = {
                        selectedText = label
                        expanded = false
                    })
                }
            }
        }

    }

    return selectedText
}

fun validatePersonalData(context: Context) {
    if (name == "" || surname == "" || birthDate == "") {
        Log.e("ERROR", context.resources.getString(R.string.invalid_form_message))
        return
    }
    val title: String = context.resources.getString(R.string.personal_data_form_title) + ":"
    val nombreLog: String = context.resources.getString(R.string.personal_data_names) + ":"
    val apellidoLog: String = context.resources.getString(R.string.personal_data_surname) + ":"
    val sexoLog: String = context.resources.getString(R.string.personal_data_sex_tittle) + ":"
    val fechaLog: String =
        context.resources.getString(R.string.personal_data_birth_date_title) + ":"
    val escolaridadLog: String =
        context.resources.getString(R.string.personal_data_schoolgrade_title) + ":"

    Log.i(title, "")
    Log.i(nombreLog, name)
    Log.i(apellidoLog, surname)
    Log.i(sexoLog, sexVal)
    Log.i(fechaLog, birthDate)
    Log.i(escolaridadLog, schoolGrade)

    val contactDataActivityIntent = Intent(context, ContactDataActivity::class.java)
    context.startActivity(contactDataActivityIntent)
}



