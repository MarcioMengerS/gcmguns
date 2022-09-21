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
* salvar (localhost:8080/add)
* listarTodos (localhost:8080/list)
* buscarPorId (localhost:8080/find/{id})
* buscarGcmPorNum(localhost:8080/number/{numero})
* excluirPorId (localhost:8080/delete/{id})
* alterar (localhost:8080/modify)
* buscarPorParteNome (localhost:8080/name)
