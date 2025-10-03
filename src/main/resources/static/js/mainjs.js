$(document).ready(function () {
  // Load profile if token exists
  $.ajax({
    type: 'GET',
    url: '/users/me',
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    beforeSend: function (xhr) {
      if (localStorage.token) {
        xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
      }
    },
    success: function (data) {
      $('#profile').html(data.username);
      // Demo image placeholder
      $('#images').attr('src', 'https://via.placeholder.com/100');
    },
    error: function () {
      // Not logged in or token invalid
    }
  });

  // Login via AJAX to obtain JWT
  $('#Login').click(function () {
    var username = $('#username').val();
    var password = $('#password').val();
    var body = JSON.stringify({ username: username, password: password });

    $.ajax({
      type: 'POST',
      url: '/auth/login',
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      data: body,
      success: function (data) {
        localStorage.token = data.token;
        window.location.href = '/user/profile';
      },
      error: function () {
        alert('Login Failed');
      }
    });
  });

  // Logout clears token
  $('#logout').click(function () {
    localStorage.clear();
    window.location.href = '/jwt/login';
  });
});


