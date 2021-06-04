<?php

namespace App\Http\Controllers\ApiController;
use App\Http\Controllers\Controller;
use App\Models\user;
use Illuminate\Support\Facades\Hash;

use Illuminate\Http\Request;

class ApiControllerAuth extends Controller
{
    public function signin(Request $request) {
        $this->validate($request, [
            'username' => 'required',
            'password' => 'required'
        ]);

        $username = $request->input('username');
        $password = $request->input('password');

        if ($user = user::where('username', $username)->first()){

            if(Hash::check($password,$user->password)){

                    $response = [
                        'username' => $user->username,
                        'pengguna' => $user->pengguna,
                        'success' => true,
                        'message' => "Successfully"
                    ];

                    return response()->json($response, 201);
                }

                $response = [
                    'msg' => 'Eror',
                ];

                return response()->json($response, 404);
            }
    }
}
