module BartHelper
  API_KEY = ENV["BART_API_KEY"]
  API_BASE = "http://api.bart.gov/api"

  def bart_url_for(action, command, params={})
    query = params.merge(:key => API_KEY, :cmd => command).
              to_a.map{|e| e.join('=')}.join('&')
    "#{API_BASE}/#{action}.aspx?#{query}"
  end
end
