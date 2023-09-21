package co.edu.udea.compumovil.gr06_20232.lab1

import CountryViewModel
import android.os.Bundle
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import co.edu.udea.compumovil.gr06_20232.lab1.ui.theme.LabsCM20232Gr06Theme

class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val cm = CountryViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20232Gr06Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactDataForm(cm)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataForm(countryModel: CountryViewModel) {
    var phoneNumberState by remember {
        mutableStateOf("")
    }
    var addressState by remember {
        mutableStateOf("")
    }
    var emailState by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit, block = {
        countryModel.getCountryList()
    })

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))

        // Telefono
        Row {
            TextField(
                value = phoneNumberState,
                onValueChange = { phoneNumberState = it},
                label = {
                        Text(
                            text = stringResource(id = R.string.contact_data_phone),
                            modifier = Modifier
                                .padding(10.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Direccion
        Row {
            TextField(
                value = addressState,
                onValueChange = { addressState = it},
                label = {
                    Text(
                        text = stringResource(id = R.string.contact_data_address),
                        modifier = Modifier
                            .padding(10.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        // email
        Row {
            TextField(
                value = emailState,
                onValueChange = { emailState = it},
                label = {
                    Text(
                        text = stringResource(id = R.string.contact_data_email),
                        modifier = Modifier
                            .padding(10.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        //countries
        CountryDropDownMenu(countryModel = countryModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropDownMenu(countryModel: CountryViewModel) {

    var expanded by remember { mutableStateOf(false) }
    val countries = countryModel.countryList
    var selectedCountry by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedCountry,
            onValueChange = { selectedCountry = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(stringResource(id = R.string.contact_data_country)) },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            countries.forEach { country ->
                DropdownMenuItem(text = { Text(text = country.name.common)},
                    onClick = {
                        selectedCountry = country.name.common
                        expanded = false
                    })
            }
        }
    }
}