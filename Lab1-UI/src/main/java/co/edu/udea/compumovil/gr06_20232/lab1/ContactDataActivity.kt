package co.edu.udea.compumovil.gr06_20232.lab1

import CountryViewModel
import android.content.Context
import android.content.res.Configuration
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import co.edu.udea.compumovil.gr06_20232.lab1.ui.theme.LabsCM20232Gr06Theme

class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val contryModel = CountryViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20232Gr06Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactDataForm(contryModel)
                }
            }
        }
    }
}

var phoneNumberState by mutableStateOf("")
var addressState by mutableStateOf("")
var emailState by mutableStateOf("")
var selectedCountry by mutableStateOf("")
var selectedCity by mutableStateOf("")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataForm(countryModel: CountryViewModel) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit, block = {
        countryModel.getCountryList()
    })

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))

        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Telefono
                    Icon(Icons.Rounded.Phone, contentDescription = "phone Icon")
                    Spacer(modifier = Modifier.width(20.dp))
                    TextField(
                        value = phoneNumberState,
                        onValueChange = { phoneNumberState = it },
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
                            imeAction = ImeAction.Next
                        )
                    )
                    // Direccion
                    Spacer(modifier = Modifier.width(30.dp))
                    Icon(Icons.Rounded.LocationOn, contentDescription = "address Icon")
                    Spacer(modifier = Modifier.width(20.dp))
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
                            autoCorrect = false,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }
            else -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.Phone, contentDescription = "phone Icon")
                    Spacer(modifier = Modifier.width(20.dp))
                    TextField(
                        value = phoneNumberState,
                        onValueChange = { phoneNumberState = it },
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
                            imeAction = ImeAction.Next
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Direccion
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.LocationOn, contentDescription = "address Icon")
                    Spacer(modifier = Modifier.width(20.dp))
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
                            autoCorrect = false,
                            imeAction = ImeAction.Next
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // email
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Email, contentDescription = "email Icon")
            Spacer(modifier = Modifier.width(20.dp))
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
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()){
                    //countries
                    CountryDropDownMenu(countryModel = countryModel)
                    //cities
                    CityDropDownMenu()
                }
            }else -> {
                //countries
                CountryDropDownMenu(countryModel = countryModel)
                //cities
                CityDropDownMenu()
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = { validateContactData(context) }, modifier = Modifier.width(100.dp)) {
            Text(text = stringResource(id = R.string.ui_next_button))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropDownMenu(countryModel: CountryViewModel) {
    val configuration = LocalConfiguration.current

    var expanded by remember { mutableStateOf(false) }
    val countries = countryModel.countryList
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(Icons.Rounded.Home, contentDescription = "country Icon")
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedTextField(
                value = selectedCountry,
                onValueChange = { selectedCountry = it },
                modifier = Modifier
                    .conditional(!(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
                        height(60.dp)
                    }
                    .conditional(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                        height(60.dp)
                        fillMaxWidth(0.45f)
                    }
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDropDownMenu() {
    val configuration = LocalConfiguration.current

    var expanded by remember { mutableStateOf(false) }
    val cities: MutableSet<String> = mutableSetOf("Bogotá", "Medellín", "Cali", "Pereira")
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(Icons.Rounded.Home, contentDescription = "country Icon")
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedTextField(
                value = selectedCity,
                onValueChange = { selectedCity = it },
                modifier = Modifier
                    .conditional(!(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)){
                        height(60.dp)
                    }
                    .conditional(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                        fillMaxWidth(0.9f)
                    }
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textfieldSize = coordinates.size.toSize()
                    },
                label = { Text(stringResource(id = R.string.contact_data_city)) },
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
                cities.forEach { city ->
                    DropdownMenuItem(text = { Text(text = city)},
                        onClick = {
                            selectedCity = city
                            expanded = false
                        })
                }
            }
        }
    }
}

fun validateContactData(context: Context) {
    if (phoneNumberState == "" || emailState == "" || selectedCountry == "" ) {
        Log.e("ERROR", context.resources.getString(R.string.invalid_form_message))
        return
    }
    val title: String = context.resources.getString(R.string.contact_data_title) + ":"
    val phone: String = context.resources.getString(R.string.contact_data_phone) + ":"
    val address: String = context.resources.getString(R.string.contact_data_address) + ":"
    val email: String = context.resources.getString(R.string.contact_data_email) + ":"
    val country: String = context.resources.getString(R.string.contact_data_country) + ":"
    val city: String = context.resources.getString(R.string.contact_data_city) + ":"

    Log.i(title, "")
    Log.i(phone, phoneNumberState)
    Log.i(address, addressState)
    Log.i(email, emailState)
    Log.i(country, selectedCountry)
    Log.i(city, selectedCity)
}

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}