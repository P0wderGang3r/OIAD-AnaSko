package functions.fundamental_knowledge;

import java.util.ArrayList;
import java.util.Arrays;

import static functions.fundamental_knowledge.ParameterType.*;

public enum AttributeName {
    Стенокардия {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "Отсутствует", "Присутствует"
        ));

        final ParameterType parameterType = БИНАРНЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Синкопе {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "Отсутствует", "Присутствует"
        ));

        final ParameterType parameterType = БИНАРНЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Тотальная_сердечная_недостаточность {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "Отсутствует", "Умеренная", "Выраженная", "Сильно выраженная"
        ));

        final ParameterType parameterType = ПЕРЕЧИСЛИМЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Увеличение_сердца {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "Отсутствует", "Умеренное", "Выраженное"
        ));

        final ParameterType parameterType = ПЕРЕЧИСЛИМЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Гипертрофия_ЭКГ {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "(10 .. 30)"
        ));

        final ParameterType parameterType = ИНТЕРВАЛЬНЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Размер_левого_желудочка {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "(21 .. 63)"
        ));

        final ParameterType parameterType = ИНТЕРВАЛЬНЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Размер_левого_предсердия {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "(19 .. 35)"
        ));

        final ParameterType parameterType = ИНТЕРВАЛЬНЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Гипертрофия_ЭхоКГ {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "Отсутствует", "Умеренная", "Выраженная"
        ));

        final ParameterType parameterType = ПЕРЕЧИСЛИМЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Индекс_сократимости_левого_желудочка_в_фазе_изгнания {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "Не изменен",
                "Незначительно снижен", "Снижен", "Значительно снижен", "Критически снижен",
                "Незначительно повышен", "Повышен", "Значительно повышен", "Критически повышен"
        ));

        final ParameterType parameterType = ПЕРЕЧИСЛИМЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    },
    Систолическое_движение_передней_створки_митрального_клапана_кпереди {
        final ArrayList<String> parametersList = new ArrayList<>(Arrays.asList(
                "Отсутствует", "Редкое", "Частое"
        ));

        final ParameterType parameterType = ПЕРЕЧИСЛИМЫЙ;

        @Override
        public ArrayList<String> getParametersList() {
            return parametersList;
        }

        @Override
        public ParameterType getParametersType() {
            return parameterType;
        }
    };

    public abstract ArrayList<String> getParametersList();
    public abstract ParameterType getParametersType();
}
