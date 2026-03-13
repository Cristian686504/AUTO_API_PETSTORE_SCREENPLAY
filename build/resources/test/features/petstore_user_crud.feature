@petstore
Feature: CRUD completo de usuarios en la API de Petstore

  Scenario Outline: Flujo completo CRUD para un usuario en la API de Petstore
    Given la API de Petstore esta disponible
    When el usuario crea un nuevo usuario con username "<username>", nombre "<firstName>", apellido "<lastName>", correo "<correo>", password "<password>", telefono "<phone>" y estado <userStatus>
    Then el codigo de estado de la respuesta debe ser 200
    When el usuario consulta el usuario con nombre de usuario "<username>"
    Then el codigo de estado de la respuesta debe ser 200
    And la respuesta debe contener el nombre de usuario "<username>"
    When el usuario actualiza el usuario "<username>" con nombre "<firstName>", apellido "<lastName>", correo "<correo_actualizado>", password "<password>", telefono "<phone>" y estado <userStatus>
    Then el codigo de estado de la respuesta debe ser 200
    When el usuario elimina el usuario con nombre de usuario "<username>"
    Then el codigo de estado de la respuesta debe ser 200

    Examples:
      | username          | firstName | lastName | correo                     | password   | phone      | userStatus | correo_actualizado       |
      | screentestqauser1 | QA        | Tester   | screentestqauser1@mail.com | Test1234!  | 3001234567 | 1          | updated_qauser1@mail.com |
