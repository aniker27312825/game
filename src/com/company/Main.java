package com.company;

import java.util.Random;

public class Main {

    public static String[] heroesNames = {"Lu Kang ", "Jax ", "Scorpion ", "Medic"};
    public static int[] heroesHealth = {270, 280, 250,480};
        public static int[] heroesStrike ={20, 15, 25, 0};

        public static String bossName = "Shao Kahn ";
        public static int bossHealth = 700;
        public static int bossStrike = 50;
        public static String superStrike = "";
        public static int roundNumber = 0;

        public static void main(String[] args) {
            // write your code here
            printStatistics();
            System.out.println("------The game started-------");

            while (!isGameFinished()){
                round();
            }
        }

        public static void round(){
            roundNumber++;
            System.out.println("-----Round " + roundNumber + "-----");
            superStrike = getSuperStrikeHero();
            bossHits();
            isMedicHealing();
            heroesHits();
            printStatistics();
        }

        public static boolean isGameFinished(){
            if (bossHealth <= 0){
                System.out.println("Heroes won!!! " +
                        "Mortal Kombat finished");
                return true;
            }

            boolean allHeroesDead = true;

            for (int heroHealth: heroesHealth) {
                if (heroHealth > 0){
                    allHeroesDead = false;
                    break;
                }
            }

            if (allHeroesDead){
                System.out.println(bossName +
                        " Won!!! Mortal Combat finished");
            }
            return allHeroesDead;
        }

        public static boolean isMedicHealing(){
            boolean medicHeal = false;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[3] > 0 && heroesHealth[i] > 0 && heroesHealth[i] < 100){
                    heroesHealth[i] = heroesHealth[i] + 50;
                    medicHeal = true;
                    System.out.println("Медик применил свою силу" + heroesNames[i] + " 50 HP");
                    break;
                } else {
                    System.out.println("Медик не смог применить свою силу") ;
                    medicHeal = false;
                    break;
                }
            }
            return medicHeal;
        }

        public static void heroesHits(){
            Random random = new Random();
            int coeff = random.nextInt(9) + 2;
            for (int i = 0; i < heroesStrike.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0){
                    if (superStrike == heroesNames[i]){
                        bossHealth = bossHealth - heroesStrike[i] * coeff;
                        System.out.println("Super strike damage "+
                                superStrike + " " + (heroesStrike[i] * coeff));
                    } else {
                        bossHealth = bossHealth - heroesStrike[i];
                    }
                }
                if (bossHealth < 0){
                    bossHealth = 0;
                }
            }
        }

        public static void bossHits(){
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0){
                    heroesHealth[i] = heroesHealth[i] - bossStrike;
                }
                if (heroesHealth[i] < 0){
                    heroesHealth[i] = 0;
                }
            }
        }

        public static String getSuperStrikeHero(){
            Random random = new Random();
            int randomIndex = random.nextInt(heroesNames.length);
            return heroesNames[randomIndex];
        }

        public static void printStatistics(){
            System.out.println(bossName + "= health " + bossHealth +
                    " strike [" + bossStrike + "]");
            for (int i = 0; i < heroesNames.length; i++) {
                System.out.println(heroesNames[i] + "= health " +
                        heroesHealth[i] + " strike [" +
                        heroesStrike[i] + "]");
            }
        }

        /*
        Добавить 4-го игрока Medic, у которого есть способность лечить после каждого раунда на N-ное количество единиц
         здоровья только одного из членов команды, имеющего здоровье менее 100 единиц. Мертвых героев медик оживлять
         не может, и лечит он до тех пор пока жив сам. Медик не участвует в бою, но получает урон от Босса

         ДЗ на сообразительность:
        ● Добавить n-го игрока, Golem, который имеет увеличенную жизнь но слабый удар. Может принимать на себя 1/5 часть урона
         исходящего от босса по другим игрокам.
        ● Добавить n-го игрока, Lucky, имеет шанс уклонения от ударов босса.
        ● Добавить n-го игрока, Berserk, блокирует часть удара босса по себе и прибавляет
        заблокированный урон к своему урону и возвращает его боссу
        ● Добавить n-го игрока, Thor, удар по боссу имеет шанс оглушить босса на 1 раунд,
        вследствие чего босс пропускает 1 раунд и не наносит урон героям.
        Дедлайн 9.04.2021 23:59
         */

    }

