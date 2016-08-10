/**
 * Created by Nico on 10.08.2016.
 */
$(function() {
    $('#delete_user').on('click',function() {

        if (!confirm('Delete User?')) {
            return false
        }

    });
});