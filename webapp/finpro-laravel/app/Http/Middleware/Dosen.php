<?php

namespace App\Http\Middleware;
use Session;
use Closure;

class Dosen
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        $session = Session::get('session_pengguna');
        if($session == null){
            return redirect()->route('user.login');
        }
        else if($session == "mahasiswa"){
            return redirect()->back();
        }
        else if($session == "dosen"){
            return $next($request);
        }
        else if($session == "koordinator")
        {
            return redirect()->back();
        }

    }
}
