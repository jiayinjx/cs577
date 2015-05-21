#!/usr/bin/python
#Programming Project 4: Traveling Salesperson Tours
#Jianyi Liu

import math
from sets import Set
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages
from pylab import *
import random

#====================================================
#  generate data set
#====================================================
points1 = [ (1, 7), (2, 0), (3, 4), (6, 5), (7, 2), (8, 6), (10, 4) ]

def UniformRandomPoints(size, xl, xu, yl, yu):
	points = []
	for i in range(size):
		x = random.uniform(xl, xu)
		y = random.uniform(yl, yu)
		points.append((x,y))
	return points
points2 = UniformRandomPoints(30, -5, 5, -5, 5)
points2 = sorted(points2, key = lambda x: x[0])

def GaussRandomPoints(size):
	points = []
	for i in range(size):
		x = random.gauss(0, 2)
		y = random.gauss(0, 2)
		points.append((x,y))
	return points
points3 = GaussRandomPoints(30)
points3 = sorted(points3, key = lambda x: x[0])

def CirclePoints(size):
	points = []
	for i in range(size):
		alpha = 2 * math.pi / float(size) * i
		r = 5
		x = r * math.cos(alpha)
		y = r * math.sin(alpha)
		points.append((x, y))
	return points
points4 = CirclePoints(30)
points4 = sorted(points4, key = lambda x: x[0])

def CirclePointsWCenters(size):
	points = []
	for i in range(size):
		alpha = 2 * math.pi / float(size) * i
		r = 5
		x = r * math.cos(alpha)
		y = r * math.sin(alpha)
		points.append((x, y))
	return points
points5 = CirclePoints(30) + UniformRandomPoints(10, -1, 1, -1, 1)
points5 = sorted(points5, key = lambda x: x[0])

def SeveralUniformRandomParts(size):
	points = []
	for i in range(size):
		x = random.uniform(-20, -17)
		y = random.uniform(-20, -17)
		points.append((x,y))
	for i2 in range(size):
		x = random.uniform(-13, -10)
		y = random.uniform(-13, -10)
		points.append((x,y))
	for i3 in range(size):
		x = random.uniform(-6, -4)
		y = random.uniform(-6, -4)
		points.append((x,y))
	for i4 in range(size):
		x = random.uniform(-1, 1)
		y = random.uniform(-1, 1)
		points.append((x,y))
	for i5 in range(size):
		x = random.uniform(4, 6)
		y = random.uniform(4, 6)
		points.append((x,y))
	for i6 in range(size):
		x = random.uniform(10, 13)
		y = random.uniform(10, 13)
		points.append((x,y))
	for i7 in range(size):
		x = random.uniform(17, 20)
		y = random.uniform(17, 20)
		points.append((x,y))
	return points
points6 = SeveralUniformRandomParts(10)
points6 = sorted(points6, key = lambda x: x[0])



#====================================================
# 2 algorithm  
#====================================================

# points:  [ (x, y),... ], assume point set is not empty

def MST_TSP(points):
	# compute all to all distance.    { (i, j): dist }
	distances = {}
	size  = len(points)
	for i in range(size):
		for j in range(i+1, size):
			pi = points[i]
			pj = points[j]
			#print pi, pj
			dist = math.sqrt( ( pi[0] - pj[0] )**2 +( pi[1] - pj[1] )**2  )
			distances[(i, j)]= dist
			distances[(j, i)]= dist
	selected_edges = []
	current = 0
	numEdge = 0
	visited = Set([current])
	while numEdge < size-1:
		# find nearest of the current one
		min_dist =  None
		min_neighbor = None
		for i in range(size):
			if i in visited:
				continue
			if min_dist == None:
				min_dist = distances[(current, i)]
				min_neighbor = i
			elif min_dist > distances[(current, i)]:
				min_dist = distances[(current, i)]
				min_neighbor = i
		selected_edges.append((current, min_neighbor, min_dist))
		visited.add(min_neighbor)
		numEdge +=1
		current = min_neighbor
	selected_edges.append((current, 0, distances[(current, 0)]))
	return selected_edges

memory = {}   #  { i:  (value, Set{ selected edges}, Set{ removed edges}) }

def Compute_B( n, distance ): # distance: { (i,j): dist }
	if n in memory:
		return memory[n][1]
	if n==0:
		memory[0] = (-1, 0, Set(), Set())
		return 0
	if n==1:
		memory[1] = (-1, distance[(0, 1)] *2, Set( [(0,1), (1,0)] ), Set() )
		return memory[1][1]
	if n==2:
		memory[2] = (-1,  distance[(0, 1)]+ distance[(1,2)] + distance[(0, 2)], Set([(0, 1), (1, 2), (0, 2)]), Set() )
		return memory[1][1]
	min_value = None
	selected_edge = Set()
	removed_edge = Set()
	index = -1
	for i in range(1, n):
		cand_selected_edge = Set( [(j, j+1) for j in range(i, n)])
		cand_selected_edge.add( (i-1, n) )
		cand_removed_edge= Set( [ (i-1, i) ] )
		list_dj_dj_1 = [  distance[(j, j+1)] for j in range(i, n)  ]
		sum_of_dj = sum(list_dj_dj_1)
		value = Compute_B(i, distance) - distance[(i-1, i)] + distance[(i-1, n)]  + sum_of_dj
		if min_value==None:
			index = i
			min_value = value
			selected_edge = cand_selected_edge
			removed_edge = cand_removed_edge
		elif value < min_value:
			index = i
			min_value = value
			selected_edge = cand_selected_edge
			removed_edge = cand_removed_edge
	memory[n] = (index, min_value, selected_edge, removed_edge)
	return min_value

def Bitonic_TSP( points ): # assume points are sorted by x-coordinate
	global memory
	memory = {}
	distances = {}
	size = len(points)
	for i in range(size):
		for j in range(i+1, size):
			pi = points[i]
			pj = points[j]
			dist = math.sqrt( ( pi[0] - pj[0] )*( pi[0] - pj[0] ) +( pi[1] - pj[1] )*( pi[1] - pj[1] ) )
			distances[(i, j)] =dist
			distances[(j, i)] =dist
	min_length = Compute_B(size-1, distances)
	current_index = size-1
	sequence  = []
	while current_index != -1:
		(parent_index, value, selected_edge, removed_edge) = memory[current_index]
		sequence.append(current_index)
		current_index = parent_index
	sequence.reverse()
	total_selected = Set()
	for index in sequence:
		(parent_index, value, selected_edge, removed_edge) = memory[index]
		total_selected.update(selected_edge)
		total_selected.difference_update(removed_edge)
	return  [ edge+ (distances[edge],)  for edge in total_selected ]

def PathLength(edges):
	return sum([ i[2] for i in edges] )


#====================================================
# plot
#====================================================
def Plot(points, edges, filename):
	xs = [ p[0] for p in points]
	ys = [ p[1] for p in points]
	fig = Figure(figsize=[2,2])
	plot(xs, ys, 'bs')
	for edge in edges:
		#print edge
		(fro, to, dist) = edge
		from_p = points[fro]
		to_p = points[to]
		lxs = [from_p[0], to_p[0]]
		lys = [from_p[1], to_p[1]]
		plot(lxs, lys, 'g-')
	#pp = PdfPages(filename)
	plt.savefig(filename, bbox_inches='tight')
	plt.close()



#====================================================
# experiment
#====================================================

#print points1
edges1_mst = MST_TSP(points1)
edges1_bit = Bitonic_TSP(points1)
print PathLength(edges1_mst)
print PathLength(edges1_bit)
Plot(points1, MST_TSP(points1), 'points1_mst.pdf')
Plot(points1, Bitonic_TSP(points1), 'points1_bit.pdf')


#print points2
edges2_mst = MST_TSP(points2)
edges2_bit = Bitonic_TSP(points2)
print PathLength(edges2_mst)
print PathLength(edges2_bit)
Plot(points2, MST_TSP(points2), 'points2_mst.pdf')
Plot(points2, Bitonic_TSP(points2), 'points2_bit.pdf')


#print points3
edges3_mst = MST_TSP(points3)
Plot(points3, MST_TSP(points3), 'points3_mst.pdf')
print PathLength(edges3_mst)
edges3_bit = Bitonic_TSP(points3)
Plot(points3, Bitonic_TSP(points3), 'points3_bit.pdf')
print PathLength(edges3_bit)


#print points4
edges4 = MST_TSP(points4)
Plot(points4, edges4, 'points4_mst.pdf')
print PathLength(edges4)
edges4_bit = Bitonic_TSP(points4)
Plot(points4, edges4, 'points4_bit.pdf')
print PathLength(edges4)


#print points5
edges5 = MST_TSP(points5)
print PathLength(edges5)
Plot(points5, edges5, 'points5_mst.pdf')
edges5 = Bitonic_TSP(points5)
print PathLength(edges5)
Plot(points5, edges5, 'points5_bit.pdf')


#print points6
edges6 = MST_TSP(points6)
print PathLength(edges6)
Plot(points6, edges6, 'points6_mst.pdf')
edges6 = Bitonic_TSP(points6)
print PathLength(edges6)
Plot(points6, edges6, 'points6_bit.pdf')
