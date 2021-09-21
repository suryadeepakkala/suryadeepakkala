package com.dataken.dao;

import com.dataken.model.Delivery;
import com.dataken.model.Match;
import com.univocity.parsers.csv.CsvFormat;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DAOUtils {

    private static final Logger log = LoggerFactory.getLogger(DAOUtils.class);
    private static MatchDAO matchDao = new MatchDAO();

    public static List<Delivery> parseDeliveries(String csvLocation) {
        List<Delivery> deliveries = new ArrayList<>();
        try (Reader inputReader = new InputStreamReader(new FileInputStream(new File(csvLocation))
                , "UTF-8")) {
            CsvParser parser = new CsvParser(csvParserSettings());
            List<String[]> parsedRows = parser.parseAll(inputReader);
            Map<Integer, Match> matchMap = new HashMap<>();
            parsedRows.forEach(row -> {
                try {
                    Delivery instance = new Delivery();
                    Match match = null;
                    int matchId = Integer.parseInt(row[0]);
                    if (!matchMap.containsKey(matchId)) {
                        match = matchDao.queryById(matchId);
                        matchMap.put(matchId, match);
                    }
                    match = matchMap.get(matchId);
                    if (match == null) {
                        log.error("Match not found with id: {}", row[0]);
                    }
                    instance.setMatch(match);
                    instance.setInning(Integer.parseInt(row[1]));
                    instance.setOver(Integer.parseInt(row[2]));
                    instance.setBall(Integer.parseInt(row[3]));
                    instance.setBatsman(row[4]);
                    instance.setNonStriker(row[5]);
                    instance.setBowler(row[6]);
                    instance.setBatsmanRuns(Integer.parseInt(row[7]));
                    instance.setExtraRuns(Integer.parseInt(row[8]));
                    instance.setTotalRuns(Integer.parseInt(row[9]));
                    instance.setNonBoundary(!row[10].equalsIgnoreCase("0"));
                    instance.setWicket(!row[11].equalsIgnoreCase("0"));
                    instance.setDismissalKind(row[12]);
                    instance.setPlayerDismissed(row[13]);
                    instance.setFielder(row[14]);
                    instance.setExtrasType(row[15]);
                    instance.setBattingTeam(row[16]);
                    instance.setBowlingTeam(row[17]);
                    deliveries.add(instance);
                } catch(Exception ex) {
                    log.error("Failed parsing the delivery row: {}", Arrays.toString(row), ex);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Date convertToDate(String s) {
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            return new Date();
        }
    }

    private static CsvParserSettings csvParserSettings() {
        CsvParserSettings settings = new CsvParserSettings();
        CsvFormat csvFormat = new CsvFormat();
        csvFormat.setDelimiter(',');
        csvFormat.setQuote('"');
        csvFormat.setQuoteEscape('\\');
        csvFormat.setCharToEscapeQuoteEscaping('\\');
        settings.setFormat(csvFormat);
        settings.setNumberOfRowsToSkip(1);
        return settings;
    }

    public static List<Match> parseMatches(String csvLocation) {
        List<Match> matches = new ArrayList<>();
        try (Reader inputReader = new InputStreamReader(new FileInputStream(
                new File(csvLocation)), "UTF-8")) {
            CsvParser parser = new CsvParser(csvParserSettings());
            List<String[]> parsedRows = parser.parseAll(inputReader);
            parsedRows.forEach(row -> {
                try {
                    Match match = new Match();
                    match.setId(Integer.parseInt(row[0]));
                    match.setCity(row[1]);
                    match.setMatchDate(convertToDate(row[2]));
                    match.setManOftheMatch(row[3]);
                    match.setVenue(row[4]);
                    match.setNeutralVenue(!row[5].equals("0"));
                    match.setFirstTeam(row[6]);
                    match.setSecondTeam(row[7]);
                    match.setTossWinner(row[8]);
                    match.setTossDecision(row[9]);
                    match.setWinner(row[10]);
                    match.setResult(row[11]);
                    if ( row[12].equalsIgnoreCase("na") ) {
                        match.setResultMargin(0);
                    } else {
                        match.setResultMargin(((Double) Double.parseDouble(row[12])).intValue());
                    }
                    match.setEliminator(!row[13].equalsIgnoreCase("N"));
                    match.setMethod(row[14]);
                    match.setUmpireOne(row[15]);
                    match.setUmpireTwo(row[16]);
                    matches.add(match);
                } catch (Exception ex) {
                    log.error("Failed parsing the match row: {}", Arrays.toString(row), ex);
                }
            });
        } catch (IOException e) {
            log.error("Failed parsing the csv file: {}", csvLocation, e);
        }
        return matches;
    }

}
