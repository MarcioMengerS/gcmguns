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

## EMAIL
### Biblioteca
Para utilizar essa funcão envio de email foi instalado a dependência spring-boot-starter-mail em sua versão 2.7.1 pois a versão 3.0 ou superior apresentou incompatibilidade com a versão starter do framework.
### Funcionalidade
O envio de email é necessário para notificar, servir de documentação, fonte de consulta e/ou comprovação de recebimento ou devolução de material pelo agente da Guarda Municipal.  
O GM ao efetuar procedimento de retirada/devolução de equipamento receberá de forma automática em sua caixa de mensagens e-mail com arquivo .pdf em anexo comprovando a operação efetuada com sucesso.
#### Métodos implementados
SendMail ==>  envia email para o destinatário  
SendMailWithAttachment ==> envia email com documento em anexo.
#### Configuração
O arquivo application.properties contém as configurações do servidor de email do remetente.
#### Arquivos
Os seguintes arquivos foram criados para realizar tal função:  
* model/Email.java  
* controllers/EmailController.java  
* service/EmailService.java
#### Conteúdo Base
Para realizar a tarefa de envio de email foi consultado o vídeo:  
https://youtu.be/aq_g8sfJNZA?si=vB6V5ClmjIKVNRgV

