package io.quarkus.sample.superheroes.fight;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.test.junit.QuarkusTestProfile;

import io.quarkus.sample.superheroes.fight.client.HeroClient;
import io.quarkus.sample.superheroes.fight.client.LocationClient;
import io.quarkus.sample.superheroes.fight.client.VillainClient;
import io.quarkus.sample.superheroes.fight.service.FightService;

/**
 * The purpose of this {@link QuarkusTestProfile} is to override the default timeouts and circuit breakers for communication with the
 * external services.
 */
public class ShorterTimeoutsProfile implements QuarkusTestProfile {
  private static final String FIGHT_SERVICE_CLASS_NAME = FightService.class.getName();
  public static final int NARRATION_OVERRIDDEN_TIMEOUT = 1;
  public static final int HELLO_OVERRIDDEN_TIMEOUT = 1;
  public static final int FIND_RANDOM_OVERRIDDEN_TIMEOUT = 1;
  public static final int FIND_RANDOM_FIGHTERS_OVERRIDDEN_TIMEOUT = FIND_RANDOM_OVERRIDDEN_TIMEOUT * 2;
  public static final int CIRCUIT_BREAKER_OVERRIDDEN_DELAY = 1;

  @Override
  // Uses the unquoted MicroProfile Fault Tolerance key format (<class>/<method>/<annotation>/<property>) because
  // @QuarkusIntegrationTest passes these as -D arguments and Windows strips the quotes required by quarkus.fault-tolerance."<class>/<method>".* keys
  public Map<String, String> getConfigOverrides() {
    var vals = new HashMap<>(Map.of(
      "%s/narrateFight/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(NARRATION_OVERRIDDEN_TIMEOUT),
      "%s/generateImageFromNarration/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(NARRATION_OVERRIDDEN_TIMEOUT),
      "%s/helloHeroes/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(HELLO_OVERRIDDEN_TIMEOUT),
      "%s/helloVillains/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(HELLO_OVERRIDDEN_TIMEOUT),
      "%s/helloNarration/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(HELLO_OVERRIDDEN_TIMEOUT),
      "%s/helloLocations/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(HELLO_OVERRIDDEN_TIMEOUT),
      "%s/findRandomFighters/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(FIND_RANDOM_FIGHTERS_OVERRIDDEN_TIMEOUT),
      "%s/findRandomLocation/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(FIND_RANDOM_OVERRIDDEN_TIMEOUT),
      "%s/findRandomHero/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(FIND_RANDOM_OVERRIDDEN_TIMEOUT),
      "%s/findRandomVillain/Timeout/value".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(FIND_RANDOM_OVERRIDDEN_TIMEOUT)
    ));
    
    vals.putAll(Map.of(
      "%s/narrateFight/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/generateImageFromNarration/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/helloHeroes/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/helloVillains/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/helloNarration/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/helloLocations/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/findRandomFighters/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/findRandomLocation/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/findRandomHero/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/findRandomVillain/Timeout/unit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS"
    ));

    vals.putAll(Map.of(
      "%s/findRandomVillain/CircuitBreaker/delay".formatted(VillainClient.class.getName()), String.valueOf(CIRCUIT_BREAKER_OVERRIDDEN_DELAY),
      "%s/findRandomHero/CircuitBreaker/delay".formatted(HeroClient.class.getName()), String.valueOf(CIRCUIT_BREAKER_OVERRIDDEN_DELAY),
      "%s/findRandomLocation/CircuitBreaker/delay".formatted(LocationClient.class.getName()), String.valueOf(CIRCUIT_BREAKER_OVERRIDDEN_DELAY),
      "%s/narrateFight/CircuitBreaker/delay".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(CIRCUIT_BREAKER_OVERRIDDEN_DELAY),
      "%s/generateImageFromNarration/CircuitBreaker/delay".formatted(FIGHT_SERVICE_CLASS_NAME), String.valueOf(CIRCUIT_BREAKER_OVERRIDDEN_DELAY),
      "%s/findRandomVillain/CircuitBreaker/delayUnit".formatted(VillainClient.class.getName()), "SECONDS",
      "%s/findRandomHero/CircuitBreaker/delayUnit".formatted(HeroClient.class.getName()), "SECONDS",
      "%s/findRandomLocation/CircuitBreaker/delayUnit".formatted(LocationClient.class.getName()), "SECONDS",
      "%s/narrateFight/CircuitBreaker/delayUnit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS",
      "%s/generateImageFromNarration/CircuitBreaker/delayUnit".formatted(FIGHT_SERVICE_CLASS_NAME), "SECONDS"
    ));

    return vals;
  }
}
