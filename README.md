<h1>Study of Java JPA</h1>
<p>
  Este README também está disponível em <a href="./README_PT-BR.md">Português Brasileiro</a>.
</p>
<p>
  This project demonstrates how to implement a clean, test-driven backend architecture using the
  <strong>Java Persistence API (JPA)</strong> with <strong>PostgreSQL</strong> and <strong>JUnit 5</strong>.
  It focuses on reusability and separation of concerns through a <strong>Generic DAO</strong> pattern and fully annotated entity classes.
</p>
<h2>🚀 Key Features</h2>
<ul>
  <li>✅ JPA-based persistence with PostgreSQL;</li>
  <li>✅ Generic DAO structure for all entities using <code>GenericDAO&lt;T&gt;</code> and <code>IGenericDAO&lt;T&gt;</code>;</li>
  <li>✅ Domain entities: <code>Course</code>, <code>Enrollment</code>, and <code>Product</code> with validation constraints and sequence generation;</li>
  <li>✅ JPQL-based dynamic queries for search and update operations;</li>
  <li>✅ JUnit 5 test classes covering full CRUD functionality for all DAOs;</li>
  <li>✅ Automatic ID management using <code>@SequenceGenerator</code> and <code>@GeneratedValue</code>;</li>
  <li>✅ JPA configuration via <code>persistence.xml</code> with PostgreSQL connection and Hibernate properties;</li>
  <li>✅ Full Javadoc coverage added for all entities, DAOs, generic classes, and test suites, including <code>@link</code> references for better navigation;</li>
  <li>✅ Comprehensive relationship mappings including One-To-Many, Many-To-Many, and One-To-One between entities;</li>
  <li>✅ Achieved 100% test coverage across all project modules.</li>
</ul>
<h2>🗂️ Project Structure</h2>
<ul>
  <li><code>br.com.eaugusto.domain</code>: Contains all JPA entity classes;</li>
  <li><code>br.com.eaugusto.dao</code>: Interfaces and classes for each entity DAO;</li>
  <li><code>br.com.eaugusto.dao.generic</code>: Reusable generic DAO interface and implementation;</li>
  <li><code>br.com.eaugusto.dao.interfaces</code>: Dedicated package for DAO interfaces to organize code;</li>
  <li><code>src/test/java</code>: Unit test classes covering all DAO methods and entity operations, including relationship tests;</li>
  <li><code>src/main/java/META-INF/persistence.xml</code>: JPA configuration file.</li>
</ul>
<h2>🧪 Testing Approach</h2>
<ul>
  <li>✅ Built entirely with <strong>JUnit 5</strong> using assertions and lifecycle annotations (<code>@BeforeEach</code>, <code>@AfterEach</code>);</li>
  <li>✅ Test coverage includes insert, update, search by ID, search all, and delete operations;</li>
  <li>✅ Relationship integrity tested with entity associations (One-To-Many, Many-To-Many, One-To-One);</li>
  <li>✅ Time-based tests for <code>Instant</code> comparisons use <code>ChronoUnit.MILLIS</code> for accuracy;</li>
  <li>✅ All tests run using a PostgreSQL database configured via <code>persistence.xml</code>.</li>
</ul>
<h2>📋 Technologies Used</h2>
<ul>
  <li>Java 17+;</li>
  <li>JPA 2.2 (Jakarta Persistence);</li>
  <li>PostgreSQL 14+;</li>
  <li>Hibernate 5.6.2.Final;</li>
  <li>JUnit 5 for testing;</li>
  <li>pgAdmin 4;</li>
  <li>Spring Tools Suite 4.</li>
</ul>
<h2>📑 Learning Objectives</h2>
<ul>
  <li>Understand how JPA annotations map Java classes to database tables;</li>
  <li>Implement a generic DAO architecture for entity management;</li>
  <li>Use JPQL for dynamic queries with entity classes;</li>
  <li>Apply TDD and verify data persistence using comprehensive unit tests;</li>
  <li>Manage complex entity relationships and cascading operations;</li>
  <li>Document code thoroughly with Javadocs for maintainability.</li>
</ul>
<h2>⚙️ Configuration</h2>
<p>
  All persistence settings can be adjusted in the <code>persistence.xml</code> file located in
  <code>src/main/java/META-INF/</code>. You can configure:
</p>
<ul>
  <li>Database URL;</li>
  <li>Username and password;</li>
  <li>Hibernate dialect and schema generation strategy;</li>
  <li>SQL logging options for debugging purposes.</li>
</ul>
