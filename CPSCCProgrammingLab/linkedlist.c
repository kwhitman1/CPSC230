#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"

list_t *create_list ()
{
    list_t *list = malloc (sizeof(list_t));

    list->first = NULL;
    list->last = NULL;
    list->size = 0;

    return list;
}

void free_list (list_t *list)
{	
	node_t *temp = list->first;
	while (temp)
	{
		node_t *x = temp->next;
		free(temp);
		temp = x;
	}
	free(list);

}

int list_size (list_t *list)
{
    return list->size;
}

void list_append (list_t *list, int item)
{
	node_t *x = malloc(sizeof(node_t));

	x->data = item;
	x->next = NULL;

	if (list->size == 0)
	{
		list->first = x;
		list->last = x;

	}
	else
	{
		list->last->next = x;
		list->last = x;
	}
	list->size++;
}

bool list_insert (list_t *list, int item, int index)
{
    if (index < 0 || index > list->size)
    {
	    return false;
    }

    node_t *x = malloc(sizeof(node_t));

    x->data = item;

    if (index == 0)
    {
	    x->next = list->first;
	    list->first = x;
	    if (list->size == 0)
	    {
		    list->last = x;
	    }
    }
    else
    {
	    node_t *y = list->first;
	    for (int i = 0; i < index - 1; i++)
	    {
		    y = y->next;
	    }
	    x->next = y->next;
	    y->next = x;

	    if (x->next == NULL)
	    {
		    list->last = x;
	    }
    }
    list->size++;
    return true;
}

bool list_remove (list_t *list, int index)
{
	if (index < 0 || index >= list->size)
	{
		return false;
	}

    node_t *temp;
    if (index == 0)
    {
	    temp = list->first;
	    list->first = list->first->next;
	    if (list->size == 1)
	    {
		    list->last = NULL;
	    }
    }
    else
    {
	    node_t *x = list->first;
	    for (int i = 0; i < index - 1; i++)
	    {
		    x = x->next;
	    }
	    temp = x->next;
	    x->next = temp->next;

	    if (index == list->size - 1)
	    {
		    list->last = x;
	    }
    }

    free(temp);
    list->size--;

    return true;
}

int list_find (list_t *list, int item)
{
	node_t *x = list->first;
	int index = 0;

	while (x)
	{
		if (x->data == item)
		{
			return index;
		}
		x = x->next;
		index++;
	}

	return -1;
}

int list_get (list_t *list, int index)
{
	if (index < 0 || index >= list->size)
	{
		return -1;
	}

	node_t *x = list->first;
	for (int i = 0; i < index; i++)
	{
		x = x->next;
	}

	return x->data;
}
