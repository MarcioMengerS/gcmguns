# Gerenciamento de Insumos GCM:
## Implementado pacote *repository*
### Classe GcmRepository
#### Atributos / Métodos:
findByNumero;
findByNomeContaining;

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
  - Nome (não pode ser nulo)
  - Número (Não pode ser nulo)
  - email
* Construtor
* Getters
* Setters

## Implementado pacote *controller*
### Classe GcmController
#### Atributos / Métodos:
* Salvar no Banco de Dados  (localhost:8080/add)
* Listar todos  (localhost:8080/list)
* Buscar por ID (localhost:8080/find/{id})
* Buscar pelo numero (localhost:8080/number/{numero})
* Excluir (localhost:8080/delete/{id})
* Alterar (localhost:8080/modify)
* Pesquisar por parte do nome (localhost:8080/name)
