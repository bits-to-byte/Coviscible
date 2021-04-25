#include <LiquidCrystal.h>


int Vo;
float R1 = 10000;
float logR2, R2, T;
float c1 = 1.009249522e-03, c2 = 2.378405444e-04, c3 = 2.019202697e-07;
//
//
unsigned long t1;

unsigned long t2;

//
//int pos = 0;    // 
const int rs = 12, en = 11, d4 = 5, d5 = 4, d6 = 3, d7 = 2;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);

int upButton = 10;
int downButton = 9;
int selectButton = 8;
int menu = 1;
int LED = 7;
unsigned long t ; 
bool x = false ; 
int i=0;

//////
//////
// ####################################
// ############# Setup ################
// ####################################

void setup() {
  lcd.begin(16, 2);

  pinMode(upButton, INPUT_PULLUP);
  pinMode(downButton, INPUT_PULLUP);
  pinMode(selectButton, INPUT_PULLUP);
  pinMode(6, OUTPUT);
  pinMode(13, INPUT); 
  pinMode(LED, OUTPUT);
  pinMode(1, OUTPUT);
  t1=millis();
  updateMenu();
}


// ####################################
// ############# Loop #################
// ####################################

void loop() 
{
  if (!digitalRead(downButton)) 
  {
    menu++;
    updateMenu();
    delay(100);
    while (!digitalRead(downButton));
  }

  if (!digitalRead(upButton)) 
  {
    menu--;
    updateMenu();
    delay(100);
    while (!digitalRead(upButton));
  }

  if (!digitalRead(selectButton)) 
  {
    execute();
    updateMenu();
    delay(100);
    while (!digitalRead(selectButton));
  }  
  
}


// ####################################
// ############# Updating #############
// ####################################

void updateMenu() {
  switch (menu) {
    case 0:
      menu = 1;
      break;
    case 1:
    lcd.clear();
    lcd.print(">Meds Reminder");
    lcd.setCursor(0, 1);
    lcd.print("Vaccs Reminder");
    break;
    case 2:
      lcd.clear();
      lcd.print(">Vaccs Reminder");
      lcd.setCursor(0, 1);
      lcd.print("Lockdown Updates");
      break;
    case 3:
      lcd.clear();
      lcd.print(">Lockdown updates");
      lcd.setCursor(0, 1);   
      lcd.print("Meds Availability");
      break;
    case 4:
      lcd.clear();
      lcd.print(">Meds Availability");
      break;
      }
}


// ####################################
// ############ Executing #############
// ####################################

         
void execute() {
  switch (menu) {
    case 0:
      break;
    case 1:
      Reminder();
      break;
    case 2:
      vaccreminder();
      break;
    case 3:
      lockdownupdates();
      break;
    case 4:
      medsavailable();
      break;
  }
}

// ####################################
// ############## Actions #############
// ####################################

// ####### Reminder ########

void Reminder(){
    lcd.clear();
    lcd.print("Govind- Time for");
    lcd.setCursor(0, 1);
    lcd.print("");
    lcd.print("Ivermectin Dose 2");
    delay(500);
}

// ####### Vacc Reminder ########

void vaccreminder() {
  lcd.clear();
  lcd.print("Govind - Time 4 ");
  lcd.setCursor(0, 1);
  lcd.print("Ur Second Dose");
  delay(150);
  lcd.print(".");
  delay(150);
  lcd.print(".");
  delay(150);
  lcd.print(".");
  lcd.print(".");
  
  digitalWrite(LED, HIGH);
  delay(500);
}
// ######### Lockdown updates #########
void lockdownupdates() {
  lcd.clear();
  lcd.print("There is lockdown");
  lcd.setCursor(0, 1);
  lcd.print("in Ur locality");
  delay(150);
  lcd.print(".");
  delay(150);
  lcd.print(".");
  delay(150);
  lcd.print(".");
  digitalWrite(LED, LOW);
  delay(500);
}

// ######## Meds Availability ########

void medsavailable() {
  lcd.clear();
  lcd.print("Ivermectin is");
  lcd.setCursor(0, 1);
  lcd.print("Available ");
  }
