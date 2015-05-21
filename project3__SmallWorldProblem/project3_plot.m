
% plot independent edge 01
filename = 'IndependentEdge_0.1.csv';
M= csvread(filename);
p = M(:,1)
density = M(:,2)
conn = M(:,3);
path_length= M(:, 4);
f = figure();
plot(p, density);
xlabel('p');
ylabel('Density');
saveas(f, 'IndependentEdge_Density_0.1.jpg');

f = figure();
plot(p, conn);
xlabel('p');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'IndependentEdge_Connectivity_p_0.1.jpg');


f = figure();
plot(p, path_length);
xlabel('p');
ylabel('Average Path Length');
saveas(f, 'IndependentEdge_Path_length_0.1.jpg');


f = figure();
plot(density, path_length);
xlabel('density');
ylabel('Average Path Length');
saveas(f, 'IndependentEdge_Density_PathLength_0.1.jpg');

f = figure();
XY = sortrows([density, conn]);
plot(XY(:, 1), XY(:, 2));
%plot(density, conn);
xlabel('Density');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'IndependentEdge_Connectivity_ED_0.1.jpg');

% plot local preference
filename = 'LocalPreference_0.1.csv';
M= csvread(filename);
alpha = M(:,1);
beta = M(:, 2);
density = M(:,3);
conn = M(:,4);
path_length= M(:, 5);

alpha = alpha(1:10:100);
beta = beta(1:10);
density = reshape(density, 10, 10);
conn = reshape(conn, 10, 10);
path_length = reshape(path_length, 10, 10);

f = figure();
surf(alpha, beta,density);
xlabel('alpha');
ylabel('beta');
zlabel('Density');
saveas(f, 'LocalPreference_Density_0.1.jpg');

f = figure();
surf(alpha, beta,conn)
xlabel('alpha');
ylabel('beta');
zlabel('Connectivity(% of connected pairs)');
saveas(f, 'LocalPreference_Connectivity_ab_0.1.jpg');


f = figure();
surf(alpha, beta,path_length)
xlabel('alpha');
ylabel('beta');
zlabel('Average Path Length');
%view(45, 0);
saveas(f, 'LocalPreference_Path_length_EdgeDensity_0.1.jpg');

density = reshape(density, 100, 1);
conn = reshape(conn, 100, 1);
f = figure();
XY = sortrows([density, conn]);
plot(XY(:, 1), XY(:, 2));
%plot(density, conn);
xlabel('Density');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'LocalPreference_Connectivity_ED_0.1.jpg');



% plot independent edge
filename = 'IndependentEdge.csv';
M= csvread(filename);
p = M(:,1)
density = M(:,2)
conn = M(:,3);
path_length= M(:, 4);
f = figure();
plot(p, density);
xlabel('p');
ylabel('Density');
saveas(f, 'IndependentEdge_Density.jpg');

f = figure();
plot(p, conn);
xlabel('p');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'IndependentEdge_Connectivity_p.jpg');


f = figure();
plot(p, path_length);
xlabel('p');
ylabel('Average Path Length');
saveas(f, 'IndependentEdge_Path_length.jpg');


f = figure();
plot(density, path_length);
xlabel('density');
ylabel('Average Path Length');
saveas(f, 'IndependentEdge_Density_PathLength.jpg');

f = figure();
XY = sortrows([density, conn]);
plot(XY(:, 1), XY(:, 2));
%plot(density, conn);
xlabel('Density');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'IndependentEdge_Connectivity_ED.jpg');

% plot local preference
filename = 'LocalPreference.csv';
M= csvread(filename);
alpha = M(:,1);
beta = M(:, 2);
density = M(:,3);
conn = M(:,4);
path_length= M(:, 5);

alpha = alpha(1:10:100);
beta = beta(1:10);
density = reshape(density, 10, 10);
conn = reshape(conn, 10, 10);
path_length = reshape(path_length, 10, 10);

f = figure();
surf(alpha, beta,density);
xlabel('alpha');
ylabel('beta');
zlabel('Density');
saveas(f, 'LocalPreference_Density.jpg');

f = figure();
surf(alpha, beta,conn)
xlabel('alpha');
ylabel('beta');
zlabel('Connectivity(% of connected pairs)');
saveas(f, 'LocalPreference_Connectivity_ab.jpg');


f = figure();
surf(alpha, beta,path_length)
xlabel('alpha');
ylabel('beta');
zlabel('Average Path Length');
view(45, -45);
saveas(f, 'LocalPreference_Path_length_EdgeDensity.jpg');

density = reshape(density, 100, 1);
conn = reshape(conn, 100, 1);
f = figure();
XY = sortrows([density, conn]);
plot(XY(:, 1), XY(:, 2));
%plot(density, conn);
xlabel('Density');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'LocalPreference_Connectivity_ED.jpg');

% plot referential attachment
filename = 'PreferentialAttachment.csv';
M= csvread(filename);
n0 = M(:,1);
m = M(:, 2);
density = M(:,3);
conn = M(:,4);
path_length= M(:, 5);

n0 = n0(1:10:100);
m = m(1:10);
density = reshape(density, 10, 10);
conn = reshape(conn, 10, 10);
path_length = reshape(path_length, 10, 10);

f = figure();
surf(n0, m,density);
xlabel('n0');
ylabel('m (% of n0)');
zlabel('Density');
saveas(f, 'PreferentialAttachment_Density.jpg');

f = figure();
surf(n0, m,conn)
xlabel('n0');
ylabel('m (% of n0)');
zlabel('Connectivity(% of connected pairs)');
saveas(f, 'PreferentialAttachment_Connectivity_n0m.jpg');


f = figure();
surf(n0, m,path_length)
xlabel('n0');
ylabel('m (% of n0)');
zlabel('Average Path Length');
%view(45, -45);
saveas(f, 'PreferentialAttachment_Path_length.jpg');

density = reshape(density, 100, 1);
conn = reshape(conn, 100, 1);
f = figure();
XY = sortrows([density, conn]);
plot(XY(:, 1), XY(:, 2));
%plot(density, conn);
xlabel('Density');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'PreferentialAttachment_Connectivity_ED.jpg');


% plot other
filename = 'Other.csv';
M= csvread(filename);
n = M(:,1);
k = M(:, 2);
density = M(:,3);
conn = M(:,4);
path_length= M(:, 5);

n = n(1:3:9);
k = k(1:3);
density = reshape(density, 3, 3);
conn = reshape(conn, 3, 3);
path_length = reshape(path_length, 3, 3);

f = figure();
surf(n, k,density);
xlabel('n');
ylabel('k');
zlabel('Density');
view(45, 60);
saveas(f, 'Other_Density.jpg');

f = figure();
surf(n, k,conn)
xlabel('n');
ylabel('k');
zlabel('Connectivity(% of connected pairs)');
saveas(f, 'Other_Connectivity_nk.jpg');



f = figure();
surf(n, k,path_length)
xlabel('n');
ylabel('k');
zlabel('Average Path Length');
%view(45, -45);
saveas(f, 'Other_Path_length.jpg');

density = reshape(density, 9, 1);
conn = reshape(conn, 9, 1);
f = figure();
XY = sortrows([density, conn]);
plot(XY(:, 1), XY(:, 2));
%plot(density,conn)
xlabel('Density');
ylabel('Connectivity(% of connected pairs)');
saveas(f, 'Other_Connectivity_ED.jpg');

