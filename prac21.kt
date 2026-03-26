data class Patient(
    val id: Int,
    var name: String,
    var age: Int,
    var disease: String,
    var admitted: Boolean = true
)

data class Doctor(
    val id: Int,
    var name: String,
    var specialization: String
)

class Hospital {
    private val patients = mutableListOf<Patient>()
    private val doctors = mutableListOf<Doctor>()

    init {
        doctors.add(Doctor(1, "Dr. Sharma", "Cardiologist"))
        doctors.add(Doctor(2, "Dr. Mehta", "Neurologist"))
        doctors.add(Doctor(3, "Dr. Patel", "Orthopedic"))
    }

    fun addPatient(patient: Patient) {
        patients.add(patient)
        println("Patient added successfully!")
    }

    fun viewPatients() {
        if (patients.isEmpty()) {
            println("No patients found.")
            return
        }

        println("\nPatients List:")
        patients.forEach {
            println("ID: ${it.id}, Name: ${it.name}, Age: ${it.age}, Disease: ${it.disease}, Admitted: ${it.admitted}")
        }
    }

    fun dischargePatient(id: Int) {
        val patient = patients.find { it.id == id }
        if (patient != null && patient.admitted) {
            patient.admitted = false
            println("Patient discharged successfully!")
        } else {
            println("Invalid patient ID or already discharged.")
        }
    }

    fun removePatient(id: Int) {
        val removed = patients.removeIf { it.id == id }
        if (removed) println("Patient removed successfully!")
        else println("Patient not found.")
    }

  
    fun viewDoctors() {
        println("\nDoctors List:")
        doctors.forEach {
            println("ID: ${it.id}, Name: ${it.name}, Specialization: ${it.specialization}")
        }
    }
}

fun main() {
    val hospital = Hospital()

    while (true) {
        println("""
            \n===== Hospital Management System =====
            1. Add Patient
            2. View Patients
            3. Discharge Patient
            4. Remove Patient
            5. View Doctors
            6. Exit
        """.trimIndent())

        print("Enter your choice: ")
        when (readLine()?.toIntOrNull()) {

            1 -> {
                print("Enter Patient ID: ")
                val id = readLine()?.toIntOrNull() ?: return

                print("Enter Name: ")
                val name = readLine() ?: ""

                print("Enter Age: ")
                val age = readLine()?.toIntOrNull() ?: return

                print("Enter Disease: ")
                val disease = readLine() ?: ""

                hospital.addPatient(Patient(id, name, age, disease))
            }

            2 -> hospital.viewPatients()

            3 -> {
                print("Enter Patient ID to Discharge: ")
                val id = readLine()?.toIntOrNull() ?: return
                hospital.dischargePatient(id)
            }

            4 -> {
                print("Enter Patient ID to Remove: ")
                val id = readLine()?.toIntOrNull() ?: return
                hospital.removePatient(id)
            }

            5 -> hospital.viewDoctors()

            6 -> {
                println("Exiting...")
                break
            }

            else -> println("Invalid choice!")
        }
    }
}