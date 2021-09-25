package devzone.healthrecords.repository;

public interface Lembrar {
   /* @Query(
            value = "SELECT DISTINCT p.* " +
                    "FROM proposition_01 p " +
                    "JOIN profile_11 p11 ON p11.a01_id = p.a01_id " +
                    "WHERE p11.a02_id = :userId AND " +
                    "p.a20_id = :organizationId",
            nativeQuery = true)
    Optional<List<Proposition>> findAllByProfile(Long userId, Long organizationId);
*/
}

