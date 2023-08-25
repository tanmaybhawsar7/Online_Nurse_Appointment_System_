all: compile test execute

run: compile execute

compile:
	mvn compile

test:
	mvn test

execute:
	java @spring_boot.argfile com.tanmayb.online_nurse_appointment_system.OnlineNurseAppointmentSystemApplication

clean:
	rm -r target/classes/com/tanmayb/online_nurse_appointment_system/*