import uvicorn
from main import app

if __name__ == "__main__":

    uvicorn.run(
        app,
        host="0.0.0.0",
        port=4000,
        timeout_graceful_shutdown=40,
        lifespan="on",
        limit_max_requests=1024 * 1024 * 1024,
    )
