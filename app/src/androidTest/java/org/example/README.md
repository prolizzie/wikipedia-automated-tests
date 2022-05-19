# Tests
Директория с тестами: `app/src/androidTest/java/org/example/tests`

# Работа с allure reports
`adb root`
`adb S=shell`
`run-as org.wikipedia.alpha sh -c 'cd /data/data/org.wikipedia.alpha/files && tar cf - allure-results' | tar xvf - -C /data/local/tmp`
`exit`
`adb pull /data/local/tmp/allure-results`
`allure generate -- clean`
`allure serve allure-results`