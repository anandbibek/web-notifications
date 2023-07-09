def get_diff_array(array1, array2):
    set1 = set(array1)
    set2 = set(array2)
    diff_set = set1 - set2
    diff_array = list(diff_set)
    return diff_array


def map_data(array1):
    return {"data": array1}
