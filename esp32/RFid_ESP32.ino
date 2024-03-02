/* 16/12/2023 - Projeto GCMSystem Versão 1 - Leitor de Cartão RFID
Modelo utilizado no projeto ESP32 Dev Module
Projeto Curto Circuito - ESP32 & RFID: Leitura de ID
Modelo utilizado no projeto ESP32S3 Dev Module
https://curtocircuito.com.br/blog/Categoria%20IoT/controle-de-acesso-pelo-telegram-com-esp32
*/
/* ---- Bibliotecas ---- */
#include <SPI.h>  /* https://github.com/PaulStoffregen/SPI */
#include <MFRC522.h> /*https://github.com/miguelbalboa/rfid */

/*---- Declara a Pinagem RST e SDA ---- */
#define SDA 5 /* Conectado ao pino D5(GPIO5) do ESP32 */
#define RST 36 /* Conectado ao pino VP(GPIO36) do ESP32*/

/* ---- LED ---- */
int LED = 2;             /* LED presente no próprio ESP32 */

MFRC522 mfrc522(SDA, RST);/* Cria uma instância MFRC522. */

void setup(){
  Serial.begin(9600);  /* Inicia a comunicação serial com o PC */
  SPI.begin();             /* Inicia SPI */
  mfrc522.PCD_Init();  /* Inicia MFRC522 */
  Serial.println("Encoste o cartao ou chaveiro no leitor");
  Serial.println();
  pinMode(LED, OUTPUT);
}
void loop(){
  if ( ! mfrc522.PICC_IsNewCardPresent()){
    /* Procura por novos cartões */
    return;
  }

  if ( ! mfrc522.PICC_ReadCardSerial()){
    /* Reenicia a leitura */
    return;
  }
  // Serial.print("ID do objeto:"); /* Mostra o valor de ID */
  String conteudo = "";
  byte letra;
  /* Rotina para despejar a matriz de bytes com os valores hexadecimais para Serial. */
  for (byte i = 0; i < mfrc522.uid.size; i++)
  { //OCT, HEX, BIN, DEC
    Serial.print(mfrc522.uid.uidByte[i] < 0x10 ? "0" : "");
    Serial.print(mfrc522.uid.uidByte[i], HEX);
    // conteudo.concat(String(mfrc522.uid.uidByte[i] < 0x10 ? "0" : ""));
    // conteudo.concat(String(mfrc522.uid.uidByte[i], HEX));
  }
  Serial.println();
  digitalWrite (LED, HIGH);
  delay(800);
  conteudo.toUpperCase(); /* Converte a String em Letras maiúsculas */
  digitalWrite (LED, LOW);
}