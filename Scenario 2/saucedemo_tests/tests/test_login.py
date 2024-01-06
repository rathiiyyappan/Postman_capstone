import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By

@pytest.fixture
def browser():
    driver = webdriver.Chrome()
    yield driver
    driver.quit()

def test_verify_title(browser):
    browser.get("https://www.saucedemo.com/")
    assert "Swag Labs" in browser.title

def test_login(browser, read_excel_data):
    username, password, expected_title = read_excel_data
    browser.get("https://www.saucedemo.com/")

    # Your implementation of login steps here

    assert expected_title in browser.title

def test_unsuccessful_login(browser, read_excel_data):
    username, password, expected_error_message = read_excel_data
    browser.get("https://www.saucedemo.com/")

    # Your implementation of unsuccessful login steps here

    error_message_element = browser.find_element(By.XPATH, "//div[@class='error-message-container error']")
    assert expected_error_message in error_message_element.text
