# Liquibase Spring Boot Demo

This repository contains the code accompanying the tutorial at <>..

## Running the application

To run this demo, run the following command

```bash
> .\gradlew bootRun
```

## Testing

The following JUnit tests verify we can write and read from the database:

```java
@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
public class HouseRepositoryTest {
    private final HouseRepository houseRepository;
    private final TestEntityManager em;

    @BeforeEach
    void before() {
        var house = new House();
        house.setOwner("Julius Krah");
        house.setFullyPaid(true);
        em.persist(house);
    }

    @Test
    @DisplayName("find house by id")
    void testFindById() {
        var house = houseRepository.findById(1);
        var condition = new Condition<House>(h -> h.isFullyPaid(), "Is fully paid");
        assertThat(house).isPresent();
        assertThat(house).hasValueSatisfying(condition);
    }
}

@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = AutowireMode.ALL)
public class ItemRepositoryTest {
    private final TestEntityManager em;
    private final ItemRepository itemRepository;

    @BeforeEach
    void before() {
        var house = new House();
        house.setOwner("James Tuffour");
        house.setFullyPaid(true);
        em.persist(house);

        var item = new Item();
        item.setName("Washing machine");
        item.setHouse(em.getEntityManager().getReference(House.class, 1));
        em.persist(item);
    }

    @Test
    @DisplayName("find item by id")
    void testFindById() {
        var item = itemRepository.findById(2);
        var condition = new Condition<Item>(i -> "Washing machine".equals(i.getName()), "Name matches 'Washing Machine'");
        assertThat(item).isPresent();
        assertThat(item).hasValueSatisfying(condition);
    }
}
```
