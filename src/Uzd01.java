import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Uzd01 {

	public static void main(String[] args) {
		int monthNum, sum = 0, k = 0, firstFeeHour = 0, lastFeeHour = 0, totalDays = 0, minutes = 0;
		int startYear, startMonth, startDay, endYear, endMonth, endDay;
		int benchmarkSaturday = 12, benchmarkSunday = 13;
		String zona = "", tmp = "", tmpZ = "", startDate = "", endDate = "", startTime = "", endTime = "";
		String[] values;
		double fee = 0, feeFraction = 0, feeTotal = 0;
		boolean sunday, saturday;
		Scanner input = new Scanner(System.in);

		while (k <= 0) {
			k++;
			System.out.println(
					"Iveskite stovejimo zona (m - melynoji, r - raudonoji, g - geltonoji, z - zalioji, b - baltoji)");
			zona = input.nextLine();
			if (!(zona.equals("m") || zona.equals("r") || zona.equals("g") || zona.equals("z") || zona.equals("b"))) {
				System.out.println("Ivesta netinkama reiksme");
				break;
			}
			switch (zona) {
			case "m":
				fee = 2.5;
				firstFeeHour = 8;
				lastFeeHour = 24;
				feeFraction = 0.5;
				break;
			case "r":
				fee = 1.5;
				firstFeeHour = 8;
				lastFeeHour = 22;
				feeFraction = 0.3;
				break;
			case "g":
				fee = 0.6;
				firstFeeHour = 8;
				lastFeeHour = 20;
				feeFraction = 0.2;
				break;
			case "z":
				fee = 0.3;
				firstFeeHour = 8;
				lastFeeHour = 18;
				feeFraction = 0.1;
				break;
			default:
				System.out.println();
				break;
			}
			System.out.println("Ar norite rezervuoti vieta menesiui ar ilgiau (t - taip, n - ne)?");
			tmp = input.nextLine();
			if (tmp.equals("t")) {
				System.out.print("Iveskite kiek menesiu norite rezervuoti vieta: ");
				monthNum = input.nextInt();

				switch (zona) {
				case "m":
					sum = 525 * monthNum;
					System.out.println("Rezervacijos mokestis yra " + sum + " Eur");
					break;
				case "r":
					sum = 315 * monthNum;
					System.out.println("Rezervacijos mokestis yra " + sum + " Eur");
					break;
				case "g":
					sum = 126 * monthNum;
					System.out.println("Rezervacijos mokestis yra " + sum + " Eur");
					break;
				case "z":
					sum = 63 * monthNum;
					System.out.println("Rezervacijos mokestis yra " + sum + " Eur");
					break;
				case "b":
					sum = 36 * monthNum;
					System.out.println("Rezervacijos mokestis yra " + sum + " Eur");
					break;
				default:
					break;
				}
				break;
			}
			if (zona.equals("g") || zona.equals("z")) {
				System.out.println("Ar norite apmoketi menesio rinkliava (t - taip, n - ne)?");
				tmp = input.nextLine();
				if (tmp.equals("t")) {
					System.out.print("Iveskite kiek menesiu norite apmoketi: ");
					monthNum = input.nextInt();

					switch (zona) {
					case "g":
						sum = 67 * monthNum;
						System.out.println("Rinkliavos mokestis yra " + sum + " Eur");
						break;
					case "z":
						sum = 33 * monthNum;
						System.out.println("Rinkliavos mokestis yra " + sum + " Eur");
						break;
					default:
						break;
					}
					break;
				}
			}
			if (zona.equals("g")) {
				System.out.println(
						"Ar norite parkuoti Turgaus, Rinktines, Gelezinkelio, Sodu ar Pylimo gatves dalyje prie Hales turgaus (t - taip, n - ne)?");
				tmp = input.nextLine();
			} else if (zona.equals("z")) {
				System.out.println("Ar norite parkuoti Zveryno kvartale, Vingio parko prieigose esancioje aiksteleje");
				System.out
						.println("ar automobiliu stovejimo aiksteleje prie Gelezinio Vilko g. 6A  (t - taip, n - ne)?");
				tmpZ = input.nextLine();
			}
			System.out.print("Iveskite pirmos parkavimo dienos data (mmmm-mm-dd): ");
			startDate = input.nextLine();
			System.out.print("Iveskite paskutines dienos data, kuria norite apmoketi (mmmm-mm-dd): ");
			endDate = input.nextLine();
			values = endDate.split("-");
			endYear = Integer.parseInt(values[0]);
			endMonth = Integer.parseInt(values[1]);
			endDay = Integer.parseInt(values[2]);
			values = startDate.split("-");
			startYear = Integer.parseInt(values[0]);
			startMonth = Integer.parseInt(values[1]);
			startDay = Integer.parseInt(values[2]);
			totalDays = getDayDifference(startYear, startMonth, startDay, endYear, endMonth, endDay);
			System.out.print("Iveskite pradzios laika (val:min): ");
			startTime = input.nextLine();
			System.out.print("Iveskite pabaigos laika (val:min): ");
			endTime = input.nextLine();
			System.out.println(totalDays);
			if (zona.equals("m")) {
			} else if (tmp.equals("t")) {
			} else if (tmpZ.equals("t")) {
				System.out.println("2");
				totalDays -= getNumSundaysOrSaturdays(startYear, startMonth, startDay, endYear, endMonth, endDay,
						benchmarkSunday);
				totalDays -= getNumSundaysOrSaturdays(startYear, startMonth, startDay, endYear, endMonth, endDay,
						benchmarkSaturday);
				sunday = checkIfDateIsSundayOrSaturday(startYear, startMonth, startDay, benchmarkSunday);
				saturday = checkIfDateIsSundayOrSaturday(startYear, startMonth, startDay, benchmarkSaturday);
				if (sunday || saturday) {
					startTime = "sunday";
					System.out.println("saturday" + saturday + "sunday" + sunday);
				}
				sunday = checkIfDateIsSundayOrSaturday(endYear, endMonth, endDay, benchmarkSunday);
				saturday = checkIfDateIsSundayOrSaturday(endYear, endMonth, endDay, benchmarkSaturday);
				if (sunday || saturday) {
					endTime = "sunday";
					System.out.println("saturday" + saturday + "sunday" + sunday);
				}
			} else {
				totalDays -= getNumSundaysOrSaturdays(startYear, startMonth, startDay, endYear, endMonth, endDay,
						benchmarkSunday);
				sunday = checkIfDateIsSundayOrSaturday(startYear, startMonth, startDay, benchmarkSunday);
				if (sunday) {
					startTime = "sunday";
				}

				sunday = checkIfDateIsSundayOrSaturday(endYear, endMonth, endDay, benchmarkSunday);
				if (sunday) {
					endTime = "sunday";
				}
			}
			minutes = getMinutesDifference(startTime, endTime, firstFeeHour, lastFeeHour, totalDays);
			feeTotal = ((minutes / 60) * fee) + ((int) ((minutes % 60) / (60 / (fee / feeFraction))) * feeFraction);
			if (feeTotal >= 0) {
				System.out.printf("Rinkliavos mokestis yra %.2f Eur", feeTotal);
			} else {
				/*
				 * Jeigu ivestas tos pacios dienos pabaigos laikas yra ankstesnis nei pradzios
				 * laikas
				 */
				System.out.println("Skaiciavimo klaida. Patikrinkite duomenis ir meginkite dar syki.");
			}
		}

	}

	private static int getMinutesDifference(String startTime, String endTime, int firstFeeHour, int lastFeeHour,
			int totalDays) {
		int startHour, startMinute, endHour, endMinute, minutes, sumStart, sumEnd, tmpSum;
		String[] values;

		if (startTime.equals("sunday")) {
			sumStart = 0;
		} else {
			values = startTime.split(":");
			startHour = Integer.parseInt(values[0]);
			startMinute = Integer.parseInt(values[1]);
			if (startHour >= firstFeeHour && startHour <= lastFeeHour) {
				sumStart = (startHour * 60 + startMinute) - firstFeeHour * 60;
			} else {
				sumStart = 0;
			}
		}
		if (endTime.equals("sunday")) {
			System.out.println(totalDays);
			if (totalDays > 1) {
				sumEnd = ((totalDays) * 24 - ((24 - (lastFeeHour - firstFeeHour)) * (totalDays))) * 60;
			} else if (totalDays == 1) {
				sumEnd = (lastFeeHour * 60) - firstFeeHour * 60;
			} else {
				sumEnd = 0;
			}
		} else {
			values = endTime.split(":");
			endHour = Integer.parseInt(values[0]);
			endMinute = Integer.parseInt(values[1]);
			if (endHour < firstFeeHour || endHour > lastFeeHour) {
				tmpSum = 0;
			} else {
				tmpSum = (endHour * 60 + endMinute) - firstFeeHour * 60;
			}
			if (totalDays > 1) {
				sumEnd = tmpSum
						+ (((totalDays - 1) * 24 - ((24 - (lastFeeHour - firstFeeHour)) * (totalDays - 1))) * 60);
			} else {
				sumEnd = tmpSum;
			}
		}
		System.out.println(sumStart);
		System.out.println(sumEnd);
		minutes = sumEnd - sumStart;
		return minutes;
	}

	private static int getDayDifference(int startYear, int startMonth, int startDay, int endYear, int endMonth,
			int endDay) {
		int days;
		LocalDate startingDay = LocalDate.of(startYear, startMonth, startDay - 1);
		LocalDate endingDay = LocalDate.of(endYear, endMonth, endDay);
		long noOfDaysBetween = ChronoUnit.DAYS.between(startingDay, endingDay);
		days = (int) noOfDaysBetween;
		return days;
	}

	private static boolean checkIfDateIsSundayOrSaturday(int year, int month, int day, int benchmark) {
		LocalDate benchmarkDay = LocalDate.of(2020, Month.DECEMBER, benchmark);
		LocalDate checkDate = LocalDate.of(year, month, day);
		long noOfDaysBetween = ChronoUnit.DAYS.between(benchmarkDay, checkDate);
		if (noOfDaysBetween % 7 == 0) {
			return true;
		} else {
			return false;
		}
	}

	private static int getNumSundaysOrSaturdays(int startYear, int startMonth, int startDay, int endYear, int endMonth,
			int endDay, int benchmark) {
		int sundaysOrS = 0;
		LocalDate benchmarkDay = LocalDate.of(2020, Month.DECEMBER, benchmark);
		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay - 1);
		long noOfDaysBetween = ChronoUnit.DAYS.between(benchmarkDay, startDate);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		long noOfDaysBetween2 = ChronoUnit.DAYS.between(benchmarkDay, endDate);
		for (int i = 0; i <= noOfDaysBetween2; i += 7) {
			if (i > noOfDaysBetween) {
				sundaysOrS++;
			}
		}
		return sundaysOrS;
	}
}
