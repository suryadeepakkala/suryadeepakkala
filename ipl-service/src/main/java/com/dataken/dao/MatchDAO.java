package com.dataken.dao;

import com.dataken.model.Match;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MatchDAO extends MainDAO {

    private static final Logger log = LoggerFactory.getLogger(MatchDAO.class);

    public void persist(Match match) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(match);
            log.info("Saved the match instance");
            System.out.println("Saved the match ...");
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed saving the match instance", ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
    }

    public void persist(List<Match> list) {
        long start = System.currentTimeMillis();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(Match match : list) {
                session.saveOrUpdate(match);
            }
            log.info("Saved the {} matches in {} ms", list.size(), (System.currentTimeMillis() - start));
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed saving the match instance", ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
    }

    public List<Match> queryMatches() {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            List<Match> matches = session.createQuery("FROM Match m").list();
            tx.commit();
            return matches;
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return new ArrayList<>();
    }

    public Match queryById(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Match match = (Match) session.createQuery("FROM Match m where id = :id").setParameter("id", id).getSingleResult();
            tx.commit();
            return match;
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the match by id: " + id, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return null;
    }

    /**
     * Returns the top ten run scorers for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryTopTenRunScorers(String season) {
        Map<String, Integer> topTenMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select year(d.match.matchDate), d.batsman, sum(d.batsmanRuns) as totalRuns from " +
                    "Delivery d where year(d.match.matchDate) = :season " +
                    "group by year(d.match.matchDate), d.batsman order by totalRuns desc";
            List stats = session.createQuery(hql).setParameter("season", Integer.parseInt(season)).setMaxResults(10).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                topTenMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return topTenMap;
    }

    /**
     * Returns the top ten wicket takers for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryTopTenWicketTakers(String season) {
        Map<String, Integer> topTenMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select year(d.match.matchDate), d.bowler, count(d.bowler) as totalWickets from " +
                    "Delivery d where year(d.match.matchDate) = :season and d.wicket = :wicket and d.dismissalKind not in :dismissals " +
                    "group by year(d.match.matchDate), d.bowler order by totalWickets desc";
            List stats = session.createQuery(hql)
                    .setParameter("season", Integer.parseInt(season))
                    .setParameter("wicket", true)
                    .setParameterList("dismissals", Arrays.asList("obstructing the field","retried hurt","run out"))
                    .setMaxResults(10)
                    .list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                topTenMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return topTenMap;
    }

    public List<Match> queryMatches(String season) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            List<Match> matches = session.createQuery("FROM Match m where year(m.matchDate) = :season order by m.matchDate")
                    .setParameter("season", Integer.parseInt(season))
                    .list();
            tx.commit();
            return matches;
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Returns the runs scored by each team for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryRunsScoredByEachTeam(String season) {
        Map<String, Integer> runsScoredMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select year(d.match.matchDate),d.battingTeam, sum(d.batsmanRuns) as totalRuns from " +
                    "Delivery d where year(d.match.matchDate) = :season " +
                    "group by year(d.match.matchDate),d.battingTeam order by totalRuns desc";
            List stats = session.createQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                runsScoredMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return runsScoredMap;
    }

    /**
     * Returns the number of matches played in each City/Venue for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryMatchesPlayedInEachCity(String season) {
        Map<String, Integer> matchesPlayedMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select year(d.match.matchDate),d.match.city, count(d.match.city) as totalCount from " +
                    "Delivery d where year(d.match.matchDate) = :season " +
                    "group by year(d.match.matchDate),d.match.city order by totalCount desc";
            List stats = session.createQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                matchesPlayedMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return matchesPlayedMap;
    }


    /**
     * Returns the top  scorers for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryTopScorers(String season) {
        Map<String, Integer> topScoreMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select year(d.match.matchDate), d.batsman, sum(d.batsmanRuns) as totalRuns from " +
                    "Delivery d where year(d.match.matchDate) = :season " +
                    "group by year(d.match.matchDate), d.batsman order by totalRuns desc";
            List stats = session.createQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                topScoreMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return topScoreMap;
    }


    /**
     * Returns the Most 100s scored by a batsman for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryCenturyScored(String season) {
        Map<String, Integer> centuryScoredMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "SELECT p.ye,p.batsman, count(*) as cc from " +
                    "(Select year(matchDate) as ye, d.batsman, d.match_id, SUM(batsmanRuns) As BR " +
                    "From deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "Where year(matchDate) = :season " +
                    "Group by year(matchDate), d.batsman, d.match_id " +
                    "having SUM(batsmanRuns) >= 100)p " +
                    "Group by p.batsman,p.ye " +
                    "order by cc desc;";
            List stats = session.createSQLQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                centuryScoredMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return centuryScoredMap;
    }

    /**
     * Returns the Most 50s scored by a batsman for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryFiftyScored(String season) {
        Map<String, Integer> fiftyScoredMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "SELECT p.ye,p.batsman, count(*) as cc from " +
                    "(Select year(matchDate) as ye, d.batsman, d.match_id, SUM(batsmanRuns) As BR " +
                    "From deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "Where year(matchDate) = :season " +
                    "Group by year(matchDate), d.batsman, d.match_id " +
                    "having SUM(batsmanRuns) >= 50 and SUM(batsmanRuns)<100)p " +
                    "Group by p.batsman,p.ye " +
                    "order by cc desc " ;
            List stats = session.createSQLQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                fiftyScoredMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return fiftyScoredMap;
    }

    /**
     * Returns the Fastest 50s scored by a batsman for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryFastestFiftyScored(String season) {
        Map<String, Integer> fastestFiftyScoredMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select * from(" +
                    "select batsman, sum(totalruns) as totalruns, count(ball) as ballsfaced from deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchdate) = :season  " +
                    "group by matchdate,venue, batsman, match_id " +
                    "order by sum(totalruns) desc " +
                    ") " +
                    "where totalruns > 50 and totalruns <100 and ballsfaced < 50 " +
                    "order by  ballsfaced asc";
            List stats = session.createQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                fastestFiftyScoredMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return fastestFiftyScoredMap;
    }

    /**
     * Returns the batting strike rate for a given season
     * @param season
     * @return
     */
    public Map<String, Double> queryBattingStrikeRate(String season) {
        Map<String, Double> battingStrikeRateMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select year(matchDate), batsman, ((cast(sum(d.batsmanRuns) as float) * 100) / count(ball)) from deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchDate) = :season " +
                    "and(extraRuns ='0' or extrasType in('noballs', 'legbyes', 'byes')) " +
                    "Group by year(matchDate), batsman " +
                    "order by ((sum(d.batsmanRuns) * 100) / count(ball) ) desc " ;
            List stats = session.createSQLQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                battingStrikeRateMap.put(row[1].toString(), Double.parseDouble(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return battingStrikeRateMap;
    }

    /**
     * Returns the batting average for a given season
     * @param season
     * @return
     */
    public Map<String, Double> queryBattingAverage(String season) {
        Map<String, Double> battingAverageMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select f.year , r.batsman , (cast(r.batsmanRuns as float) / f.dismissals as average) " +
                    "from  " +
                    "(select year(matchdate) as year, d.playerdismissed as playerdismissed, count(d.playerdismissed) as dismissals  " +
                    "from deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchdate) = :season " +
                    "Group by year(matchdate), d.playerdismissed)  f " +
                    "inner join  " +
                    "( " +
                    "select year(matchdate) as year, d.batsman, sum(d.batsmanRuns) as batsmanRuns  " +
                    "from deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchdate) = :season " +
                    "Group by year(matchdate), batsman " +
                    "order by ((sum(d.batsmanRuns) * 100) / count(ball) ) desc ) r on f.playerdismissed = r.batsman " +
                    "order by average desc";
            List stats = session.createSQLQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                battingAverageMap.put(row[1].toString(), Double.parseDouble(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return battingAverageMap;
    }

    /**
     * Returns the maidens bowled by a bowler  for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryMaidensBowled(String season) {
        Map<String, Integer> maidensBowledMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "SELECT p. matchyear,p.Bowler, COUNT(*) from  ( " +
                    "select year(matchdate) as matchyear, bowler, SUM(batsmanRuns),d.match_id, OVER, COUNT(ball) as cc " +
                    "from deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchdate) = :season " +
                    "AND (ball between 1 ANd 12) " +
                    "Group by year(matchdate), bowler, d.match_id, OVER " +
                    "HAVING SUM(batsmanRuns) = 0 AND COUNT(ball) = 6 )p " +
                    "GROUP BY p. matchyear,p.Bowler " +
                    "ORDER BY COUNT(*) desc";
            List stats = session.createSQLQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                maidensBowledMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return maidensBowledMap;
    }

    /**
     * Returns the most dot balls bowled by a bowler  for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryDotBallsBowled(String season) {
        Map<String, Integer> dotBallsBowledMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "SELECT p. matchyear,p.Bowler, SUM(CC) from  ( " +
                    "select year(matchdate) as matchyear, bowler, batsmanRuns,d.match_id,  COUNT(BALL) as cc " +
                    "from deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchdate) = :season " +
                    "AND batsmanRuns = 0 " +
                    "AND (extrasType = 'NA' OR extrasType ='legbyes' OR ( extrasType = 'byes' AND EXtRARuns < '5')  ) " +

                    "Group by year(matchdate), bowler, d.match_id, batsmanRuns " +
                    "  )p " +
                    "GROUP BY p. matchyear,p.Bowler " +
                    "ORDER BY SUM(CC) desc";
            List stats = session.createSQLQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                dotBallsBowledMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return dotBallsBowledMap;
    }

    /**
     * Returns the bowling economy by a bowler for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryBowlingEconomy(String season) {
        Map<String, Integer> bowlingEconomyMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "select bowler, (totalruns)/(overs) as economy from " +
                    "(select bowler, count(ball), sum(totalRuns) as totalruns, (count(ball) ) /(6) as overs from  deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchdate) = :season " +
                    "group by bowler) " +
                    "where overs > 0 " +
                    "order by economy asc ";
            List stats = session.createQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                bowlingEconomyMap.put(row[0].toString(), Integer.parseInt(row[1].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return bowlingEconomyMap;
    }

    /**
     * Returns the runs conceded in an over for a given season
     * @param season
     * @return
     */
    public Map<String, Integer> queryRunsConceded(String season) {
        Map<String, Integer> runsConcededMap = new LinkedHashMap<>();
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String hql = "SELECT p. matchyear,p.Bowler, SUM(CC) from  (" +
                    "select  year(matchdate) as matchyear, bowler,SUM(batsmanRuns+extraRuns)CC,d.match_id,  OVER " +
                    "from deliveries d " +
                    "inner join matches m on d.match_id = m.id " +
                    "where year(matchdate) = :season " +
                    "AND (EXtraStype != 'legbyes' ) " +
                    "Group by year(matchdate), bowler, d.match_id, OVER " +
                    "  )p " +
                    "GROUP BY p. matchyear,p.Bowler,p.Match_ID " +
                    "ORDER BY SUM(CC) desc";
            List stats = session.createSQLQuery(hql).setParameter("season", Integer.parseInt(season)).list();
            stats.forEach(stat -> {
                Object[] row = (Object[]) stat;
                runsConcededMap.put(row[1].toString(), Integer.parseInt(row[2].toString()));
            });
            tx.commit();
        } catch (Exception ex) {
            if ( tx != null ) {
                tx.rollback();
            }
            log.error("Failed querying the top ten run scorers for season" + season, ex);
        } finally {
            if ( session != null ) {
                session.close();
            }
        }
        return runsConcededMap;
    }

}

