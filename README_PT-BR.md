<h1>Estudo de Java com JPA</h1>
<p>
  This README is also available in <a href="./README.md">English</a>.
</p>
<p>
  Este projeto demonstra como implementar uma arquitetura backend limpa e orientada a testes utilizando a
  <strong>Java Persistence API (JPA)</strong> com <strong>PostgreSQL</strong> e <strong>JUnit 5</strong>.
  O foco está na reutilização de código e separação de responsabilidades com um padrão de <strong>DAO Genérico</strong> e entidades mapeadas com anotações JPA.
</p>
<h2>🚀 Principais Funcionalidades</h2>
<ul>
  <li>✅ Persistência com JPA utilizando PostgreSQL;</li>
  <li>✅ Estrutura de DAO genérico com <code>GenericDAO&lt;T&gt;</code> e <code>IGenericDAO&lt;T&gt;</code> reutilizáveis;</li>
  <li>✅ Entidades do domínio: <code>Course</code>, <code>Enrollment</code> e <code>Product</code> com validações e geração de ID via sequência;</li>
  <li>✅ Consultas dinâmicas com JPQL para busca e atualização de entidades;</li>
  <li>✅ Testes unitários com JUnit 5 cobrindo todas as operações CRUD;</li>
  <li>✅ Gerenciamento automático de IDs com <code>@SequenceGenerator</code> e <code>@GeneratedValue</code>;</li>
  <li>✅ Configuração JPA via <code>persistence.xml</code> com parâmetros de conexão PostgreSQL e propriedades Hibernate;</li>
  <li>✅ Cobertura completa de Javadoc adicionada a todas as entidades, DAOs, classes genéricas e testes, incluindo referências <code>@link</code> para facilitar a navegação;</li>
  <li>✅ Mapeamentos completos de relacionamentos entre entidades, incluindo One-To-Many, Many-To-Many e One-To-One;</li>
  <li>✅ Alcance de 100% de cobertura de testes em todos os módulos do projeto.</li>
</ul>
<h2>🗂️ Estrutura do Projeto</h2>
<ul>
  <li><code>br.com.eaugusto.domain</code>: Contém todas as classes de entidade JPA;</li>
  <li><code>br.com.eaugusto.dao</code>: Interfaces e implementações DAO específicas para cada entidade;</li>
  <li><code>br.com.eaugusto.dao.generic</code>: Interface e classe DAO genéricas reutilizáveis;</li>
  <li><code>br.com.eaugusto.dao.interfaces</code>: Pacote dedicado para interfaces DAO, melhorando organização do código;</li>
  <li><code>src/test/java</code>: Classes de testes unitários cobrindo todos os métodos DAO e operações nas entidades, incluindo testes de relacionamentos;</li>
  <li><code>src/main/java/META-INF/persistence.xml</code>: Arquivo de configuração JPA.</li>
</ul>
<h2>🧪 Abordagem de Testes</h2>
<ul>
  <li>✅ Desenvolvido com <strong>JUnit 5</strong> utilizando anotações como <code>@BeforeEach</code> e <code>@AfterEach</code>;</li>
  <li>✅ Cobertura de testes inclui: inserção, atualização, busca por ID, busca geral e exclusão;</li>
  <li>✅ Testes de integridade dos relacionamentos com associações (One-To-Many, Many-To-Many, One-To-One);</li>
  <li>✅ Para testes de datas, usa <code>ChronoUnit.MILLIS</code> para evitar falhas de precisão em milissegundos entre Java e o banco;</li>
  <li>✅ Todos os testes utilizam uma conexão PostgreSQL configurada no <code>persistence.xml</code>.</li>
</ul>
<h2>📋 Tecnologias Utilizadas</h2>
<ul>
  <li>Java 17+;</li>
  <li>JPA 2.2 (Jakarta Persistence);</li>
  <li>PostgreSQL 14+;</li>
  <li>Hibernate 5.6.2.Final;</li>
  <li>JUnit 5 para testes unitários;</li>
  <li>pgAdmin 4;</li>
  <li>Spring Tools Suite 4.</li>
</ul>
<h2>📑 Objetivos de Aprendizado</h2>
<ul>
  <li>Compreender como mapear entidades com anotações JPA;</li>
  <li>Implementar um DAO genérico para gerenciamento de entidades;</li>
  <li>Utilizar JPQL para consultas dinâmicas com base nas entidades;</li>
  <li>Aplicar TDD e verificar a persistência correta de dados com testes automatizados;</li>
  <li>Gerenciar relacionamentos complexos entre entidades e operações de cascata;</li>
  <li>Documentar o código de forma completa com Javadocs para facilitar manutenção.</li>
</ul>
<h2>⚙️ Configuração</h2>
<p>
  Todas as configurações de persistência estão no arquivo <code>persistence.xml</code> localizado em
  <code>src/main/java/META-INF/</code>. Você pode ajustar:
</p>
<ul>
  <li>URL de conexão com o banco de dados;</li>
  <li>Usuário e senha do banco;</li>
  <li>Dialeto Hibernate e estratégia de geração de schema;</li>
  <li>Logs SQL para debug durante o desenvolvimento.</li>
</ul>
