public interface CountryRepository extends Repository<Country, BigInteger> {

    //------------------------------------------- equality
    
    public Country findByName(String countryName);
    
    @Query("{name : ?0}")
    public Country findByNameQuery(String countryName);

    //------------------------------------------- not equal
    
    public List<Country> findByNameNot(String countryName);

    @Query("{name : {$ne : ?0}}")
    public List<Country> findByNameNotQuery(String countryName);

    //------------------------------------------- like / regex

    public List<Country> findByNameLike(String countryName);

    public List<Country> readByNameRegex(String countryName);
    
    @Query("{name : {$regex : ?0}}")
    public List<Country> getByNameRegexQuery(String countryName);
    
    //------------------------------------------- nested
    
    public List<Country> findByContinentName(String continentName);
    
    @Query("{'continent.name' : ?0}")
    public List<Country> findByContinentNameQuery(String continentName);
    
    //------------------------------------------- null / not null
    
    public List<Country> findByPopulationIsNull();
    
    @Query("{'population' : null}")
    public List<Country> findByPopulationIsNullQuery();

    public List<Country> findByPopulationIsNotNull();

    @Query("{'population' : {$ne : null}}")
    public List<Country> findByPopulationIsNotNullQuery();
    
    //------------------------------------------- less than / greater than

    public List<Country> findByAreaInSquareMilesLessThan(int area);

    @Query("{'area' : {$lt : ?0}}")
    public List<Country> findByAreaInSquareMilesLessThanQuery(int area);

    public List<Country> findByPopulationGreaterThan(int population);

    @Query("{'population' : {$gt : ?0}}")
    public List<Country> findByPopulationGreaterThanQuery(int population);
    
    //------------------------------------------- between
    
    public List<Country> findByPopulationBetween(int start, int end);
    
    @Query("{'population' : {$gt : ?0, $lt : ?1}}")
    public List<Country> findByPopulationBetweenQuery(int start, int end);
    
    //------------------------------------------- and

    public List<Country> findByContinentNameAndPopulationLessThan(String continentName, int pop);

    @Query("{'continent.name' : ?0, population : {$lt : ?1}}")
    public List<Country> findByContinentNameAndPopulationLessThanQuery(String continentName, int pop);
    
    //------------------------------------------- or
    
    public List<Country> findByPopulationLessThanOrAreaInSquareMilesLessThan(int pop, int area);
    
    @Query("{'$or' : [{'population' : {$lt : ?0}}, {'area' : {$lt : ?1}}]}")
    public List<Country> findByPopulationLessThanOrAreaInSquareMilesLessThanQuery(int pop, int area);

    //------------------------------------------- order by
    
    public List<Country> findByContinentNameOrderByPopulationDesc(String continentName);

    //------------------------------------------- fields
    
    @Query(value="{'continent.name' : ?0}", fields="{_id : 0, name : 1}")
    public List<Country> findByContinentNameJustReturnNameQuery(String continentName);
}
