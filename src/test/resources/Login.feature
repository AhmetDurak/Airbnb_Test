Feature: User should be able to rent a room

  @airbnb
  Scenario: User should be able to rent a room in selected conditions
    Given landing home page
    Then select "where"
    And click "italy"
    Then select "when"
    And click "month.april"
    Then select "who"
    And click "search"
    And click "filter"
    Then select checkboxes like "Englisch, WLAN, Bügeleisen, Barrierefreier, Englisch"
    And click "show result"
    Then select result 1
    Then select date 24
    Then select date 30
    Then click "reserve"


  @airbnb
  Scenario: rent a room in selected conditions


