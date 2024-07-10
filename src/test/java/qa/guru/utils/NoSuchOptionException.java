package qa.guru.utils;

public class NoSuchOptionException extends Exception{
        public NoSuchOptionException() {
            super("Нет такой опции! Выберите опции 4, 8 или 12");
        }
    }

