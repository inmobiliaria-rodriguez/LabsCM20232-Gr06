package co.edu.udea.compumovil.gr06_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr06_20232.lab1.ui.theme.LabsCM20232Gr06Theme

class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsCM20232Gr06Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactDataForm()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataForm() {
    var phoneNumberState by remember {
        mutableStateOf("")
    }
    var addressState by remember {
        mutableStateOf("")
    }
    var emailState by remember {
        mutableStateOf("")
    }

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
    }
}
