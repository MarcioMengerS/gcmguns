# Sistema para Gerenciamento de Materiais GCM:
## Implementado pacote *repository*
### Classe GcmRepository responsável por fazer a conexão com o banco de dados
#### Atributos / Métodos:
* findByNumero;
* findByNomeContaining;

## Implementado pacote *service*
### Classe GcmService
#### Atributos / Métodos:
* Atributo do tipo GcmRepository
* Construtor que recebe objeto do tipo GcmRepository 
* save
* listAll
* findById
* findByNumber
* findByNameContaining
* deleteById

## Implementado pacote *model*
### Classe Gcm
#### Atributos / Métodos:
* Atributos
  - Id (gerado automaticamente pelo banco)
  - Número (Não pode ser nulo)
  - Nome (não pode ser nulo)
  - cpf
  - dataNas
  - dataAdmis
  - email
  - enderecos
* construtor()
* calcularIdade()
* calcularAnosServico()
* getters
* setters

## Implementado pacote *controller*
### Classe GcmController
#### Método / Rota / Verbo:
* Create - localhost:8080/gcm - POST
* FindAll - localhost:8080/gcm - GET
* Delete - localhost:8080/gcm/{id} - DELETE
* Update - localhost:8080/gcm/{id} - PUT
* FindById - localhost:8080/gcm/{id} - GET
* FindByNameContaining - localhost:8080/gcm/name?nome={parteNome} - GET
* FindByNumber - localhost:8080/gcm/num/{numero} - GET
* GetAge - localhost:8080/gcm/age/{numero} - GET
* GetYearsService - localhost:8080/gcm/temp-serv/{numero} - GET

## Implementado pacote *Configuration*
### Classe CorsConfiguration
#### Método:
* addCorsMappings (CorsRegistry registry)


28/03/2023
## MÓDULO openPDF
### Uilizado para produzir documento denominado cautela (comprovante de entrega e devolução de material) no formato pdf.

https://mvnrepository.com/artifact/com.github.librepdf/openpdf

Adicionado ao arquivo pom.xml o trecho de código abaixo:

```
  <dependency>
    <groupId>com.github.librepdf</groupId>
    <artifactId>openpdf</artifactId>
    <version>1.3.29</version>
  </dependency>
```
### As seguintes classes foram incorporadas ao projeto:

* PdfGeneratorService.java no pacote service
    * Conteúdo o documento, como formatação e textos.
* PdfExportController.java no pacote controllers
    * Rota para geração de em formato pdf:
      * GET - localhost:8080/pdf/generate


### Modelo de cautela produzido com openPDF devolvido através da rota designada.

![Cautela](/src/main/java/br/com/gcm/sac/setor_armamento/images/modeloCautela.JPG)

## TOKEN
### Logout JWT token
Sair do sistema (Logout) só é possivel após expiração do token JWT. O modo escolhido para fazer logout foi excluir com javascript, no frontend, o token recebido. Para isso no arquivo __index.js__ foi implementada a função logout, que exclui a váriavel "token" implementada na sessionStorage que armazena o token necessário para autenticar as rotas.
### Exceções
Quando usuário solicitava uma requisição com token inválido ou expirado o sistema retornava com status 500, para tratar esse erro foi desenvolvida a classe __TokenInvalidException.java__ que agora retorna status 403 Forbidden e mensagem "token inválido ou expirado".

c
Para utilizar essa função envio de email foi instalado a dependência spring-boot-starter-mail em sua versão 2.7.1 pois a versão 3.0 ou superior apresentou incompatibilidade com a versão starter do framework.
### Funcionalidade
O envio de email é necessário para notificar, servir de documentação, fonte de consulta e/ou comprovação de recebimento ou devolução de material pelo agente da Guarda Municipal, como ojetivo de evitar impressão de papel.  
O GM ao efetuar procedimento de retirada/devolução de equipamento receberá de forma automática em sua caixa de mensagens e-mail com arquivo .pdf em anexo comprovando a operação efetuada com sucesso. Nessa primeira versão não há consolidação das informações de email e anexo.pdf.
### Método implementado
sendMailWithAttachment() ==> envia email com documento em anexo.
### Configuração
O arquivo application.properties contém as configurações do servidor de email do remetente como:  
* Host, Port, Usarname, Password, Smtp
### Arquivos
Os seguintes arquivos foram criados para realizar tal função:  
* controllers/EmailController.java  
* service/EmailService.java
### Base de Conhecimento
Para realizar a tarefa de envio de email foi consultado o vídeo:  
https://youtu.be/aq_g8sfJNZA?si=vB6V5ClmjIKVNRgV
## PDF
### Biblioteca
O pacote **itextpd** em sua versão 5.5.13.3 foi adicionado ao arquivo POM.xml para gerar documentos com conteúdos no formato PDF. Os documentos serão enviados por email e gerados eventualmente de forma automática.
### Funcionalidade
A função recebe objeto das classe *GCM* e *Equipment* e com esses dados preencher de forma automática o documento a ser enviado ao destino. Escolhida a forma de retorno da função em bytes para se anexar a mensagem que será enviada por email.
### Método implementado
`public byte[] createPdf(Gcm gcm,Equipment equipment)`
### Arquivos
Arquivo criado para realizar tal função:  
* service/PdfGeneratorService.java
PdfGeneratorService
## WebSocket
### Biblioteca
O pacote instalado para uso foi do prórprio framework, o spring boot starter websocket 3.2.2. As razões para uso dessa tecnologia foi a cominicação aberta entre servidores. O IOT ESP32+módulo RFID é um servidor que enviará informações para o backend