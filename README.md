# Matrixes
1. Реализовать классы/процедуры: 
	• для работы с векторами (сложение, вычитание, скалярное умножение, вычисление нормы как максимальной по модулю компоненты, считывание из файла / с экрана, вывод в файл / на экран); 
	• для работы с трёхдиагональными матрицами (сложение, вычитание, умножение, умножение на вектор, считывание из файла / с экрана, вывод в файл / на экран; при задании матриц предусматривать, что a1= сn= 0). 

2. Реализовать методы/процедуры вычисления вектора x по заданным векторам a, b, c и d двумя различными способами: методом прогонки (п. 2) и неустойчивым методом (п. 3). 

3. Способы задания входных данных: 
	• из файла (размерность системы n также задаётся из файла); 
	• случайными числами (в этом случае размерность системы n задаётся не из файла, а с клавиатуры). 

4. Режимы работы: 
	• основной режим: 
		◦ на входе – векторы a, b, c, d; 
		◦ на выходе – найденный вектор x; 
	• режим тестирования: 
		◦ на входе – векторы  a, b, c, а также x∗ — заранее известное точное решение; 
		◦ вычисляется соответствующий точному решению x∗ вектор правой части d; 
		◦ вектор решения x вычисляется, как и в общем случае, по векторам  a, b, c и d; 
		◦ на выходе – (помимо найденного вектора x ) погрешность ∥x∗−x∥=max 1⩽i⩽n ∣xi∗−xi∣. 
		
5. Составить таблицу погрешностей двух рассматриваемых методов. 
	• 1 столбец — размерность системы n=10,20,...; 
	• 2 столбец — погрешность метода прогонки; 
	• 3 столбец — погрешность неустойчивого метода.
