% part 1.1
% read file, x: time, y: probility
filename = 'part1_1.csv';
M = csvread(filename);
% plot figure
x = M(:, 1);
y = M(:, 2);
figure;
plot(x, y)
% part 1.2, x: number of visited, y: number of cells
filename = 'part1_21.csv';
M = csvread(filename);
% plot figure
x = M(:, 1);
y = M(:, 2);
figure;
plot(x, y)

% part 1.22, x: cell index, y: number of visited
filename = 'part1_22.csv';
M = csvread(filename);
% plot figure
x = M(:, 1);
y = M(:, 2);
figure;
plot(x, y)


% part 2.1, x: time, y: probility
filename = 'part2_1.csv';
M = csvread(filename);
% plot figure
x = M(:, 1);
y = M(:, 2);
figure;
plot(x, y)

% part 2.2, x: number of cells visited, y: probility
filename = 'part2_2.csv';
M = csvread(filename);
% plot figure
x = M(:, 1);
y = M(:, 2);
figure;
plot(x, y)
% part 2.3, x: cell index, y: probility
filename = 'part2_3.csv';
M = csvread(filename);
% plot figure
x = [1:1:50];
y = [1:1:50];
p = M(:, 3);
p = reshape(p, 50, 50);
p = transpose(p);
figure;
surf(x, y, p,'EdgeColor','none');

% part 2.41, x: time, y: probility
filename = 'part2_41.csv';
M = csvread(filename);
% plot figure
x = M(:, 1);
y = M(:, 2);
figure;
plot(x, y)


% part 2.42, x: cell index, y: probility
filename = 'part2_42.csv';
M = csvread(filename);
% plot figure
x = [1:1:50];
y = [1:1:50];
p = M(:, 3);
p = reshape(p, 50, 50);
p = transpose(p);
figure;
surf(x, y, p,'EdgeColor','none');